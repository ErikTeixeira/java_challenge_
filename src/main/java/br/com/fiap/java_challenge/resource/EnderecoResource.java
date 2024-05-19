package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.EnderecoRequest;
import br.com.fiap.java_challenge.dto.response.EnderecoResponse;
import br.com.fiap.java_challenge.entity.Endereco;
import br.com.fiap.java_challenge.service.EnderecoService;
import br.com.fiap.java_challenge.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource implements ResourceDTO<Endereco, EnderecoRequest, EnderecoResponse> {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<Collection<EnderecoResponse>> findAll(
            @RequestParam(name="cep", required = false) String cep,
            @RequestParam(name="numero", required = false) String numero,
            @RequestParam(name="complemento", required = false) String complemento,
            @RequestParam(name="usuario.ID", required = false) Long usuarioId
    ) {

        var usuario = Objects.nonNull(usuarioId) ? usuarioService.findById(usuarioId) : null;

        var endereco = Endereco.builder()
                .cep(cep)
                .numero(numero)
                .complemento(complemento)
                .usuario(usuario)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Endereco> example = Example.of(endereco, matcher);

        var encontrados = enderecoService.findAll(example);

        if(encontrados.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = encontrados.stream()
                .map( enderecoService::toResponse )
                .toList();

        return ResponseEntity.ok(resposta);
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoResponse> findById(@PathVariable Long id) {

        var encontrado = enderecoService.findById( id );

        if (Objects.isNull( encontrado )) return ResponseEntity.notFound().build();

        var resposta = enderecoService.toResponse( encontrado );

        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<EnderecoResponse> save(@RequestBody @Valid EnderecoRequest r) {

        var entity = enderecoService.toEntity(r);
        var saved = enderecoService.save(entity);
        var response = enderecoService.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
