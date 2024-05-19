package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.EstabelecimentoRequest;
import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.EstabelecimentoResponse;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.entity.Pessoa;
import br.com.fiap.java_challenge.entity.PreferenciaViagem;
import br.com.fiap.java_challenge.entity.Usuario;
import br.com.fiap.java_challenge.service.EstabelecimentoService;
import br.com.fiap.java_challenge.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements ResourceDTO<Usuario, UsuarioRequest, UsuarioResponse> {

    @Autowired
    UsuarioService service;

    @Autowired
    EstabelecimentoService estabelecimentoService;


    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(

            @RequestParam(name = "username", required = false) String username,

            @RequestParam(name="pessoaId", required = false) Long pessoaId,
            @RequestParam(name = "pessoa Nome", required = false) String pessoaNome,
            @RequestParam(name = "pessoa Sobrenome", required = false) String pessoaSobrenome,
            @RequestParam(name = "pessoa Nascimento", required = false) LocalDate pessoaNascimento,
            @RequestParam(name = "pessoa Email", required = false) String pessoaEmail,

            @RequestParam(name = "Tipo culinaria", required = false) String tipoCulinaria,
            @RequestParam(name = "Restricoes alimentares", required = false) String restriAlimentar,
            @RequestParam(name = "Tipo transporte", required = false) String tipoTransporte,
            @RequestParam(name = "Tipo hospedagem", required = false) String tipoHospedagem,
            @RequestParam(name = "Viaja sozinho", required = false) String viajaSozinho

    ) {

        var preferenciaViagem = PreferenciaViagem.builder()
                .tipo_culinaria(tipoCulinaria)
                .restricoes_alimentares(restriAlimentar)
                .tipo_transporte(tipoTransporte)
                .tipo_hospedagem(tipoHospedagem)
                .viaja_sozinho(viajaSozinho)
                .build();

        var pessoa = Pessoa.builder()
                .id(pessoaId)
                .nome(pessoaNome)
                .sobrenome(pessoaSobrenome)
                .dataNascimento(pessoaNascimento)
                .email(pessoaEmail)
                .build();

        var usuario = Usuario.builder()
                .username(username)
                .pessoa(pessoa)
                .preferenciaViagem(preferenciaViagem)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Usuario> example = Example.of(usuario, matcher);
        Collection<Usuario> usuarios = service.findAll(example);

        var response = usuarios.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {

        var encontrado = service.findById( id );

        if (Objects.isNull( encontrado )) return ResponseEntity.notFound().build();

        var resposta = service.toResponse( encontrado );

        return ResponseEntity.ok( resposta );
    }


    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {

        var entity = service.toEntity( r );
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created( uri ).body( resposta );
    }


    @Transactional
    @PostMapping(value = "/{id}/estabelecimentos")
    public ResponseEntity<UsuarioResponse> saveEstabelecimento(@PathVariable Long id, @RequestBody @Valid EstabelecimentoRequest r) {
        var usuario = service.findById(id);
        if (usuario == null) return ResponseEntity.notFound().build();

        var estabelecimento = estabelecimentoService.toEntity(r);
        var estabelecimentos = usuario.getEstabelecimentos();
        estabelecimentos.add(estabelecimento);
        usuario.setEstabelecimentos(estabelecimentos);

        var saved = service.save(usuario);
        var resposta = service.toResponse(saved);


        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @Transactional
    @GetMapping(value="/{id}/estabelecimentos")
    public ResponseEntity<Collection<EstabelecimentoResponse>> findAcessorios(@PathVariable Long id)
    {
        var usuario = service.findById( id );
        if (usuario == null) return ResponseEntity.notFound().build();

        var estabelecimentos = usuario.getEstabelecimentos();
        var resposta = estabelecimentos.stream()
                .map( estabelecimentoService::toResponse )
                .toList();

        if(resposta.isEmpty()) return ResponseEntity.noContent().build();


        return ResponseEntity.ok( resposta );
    }


}
