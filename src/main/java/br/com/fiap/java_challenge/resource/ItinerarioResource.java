package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.ItinerarioRequest;
import br.com.fiap.java_challenge.dto.response.ItinerarioResponse;
import br.com.fiap.java_challenge.entity.Itinerario;
import br.com.fiap.java_challenge.service.ItinerarioService;
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
@RequestMapping(value = "/itinerario")
public class ItinerarioResource implements ResourceDTO<Itinerario, ItinerarioRequest, ItinerarioResponse> {

    @Autowired
    private ItinerarioService itinerarioService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<Collection<ItinerarioResponse>> findAll(
            @RequestParam(name="descricao", required = false) String descricao,
            @RequestParam(name="usuario.ID", required = false) Long usuarioId
    ) {

        var usuario = Objects.nonNull(usuarioId) ? usuarioService.findById(usuarioId) : null;

        var itinerario = Itinerario.builder()
                .descricao(descricao)
                .usuario(usuario)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Itinerario> example = Example.of(itinerario, matcher);

        var encontrados = itinerarioService.findAll(example);

        if(encontrados.isEmpty()) return ResponseEntity.noContent().build();

        var resposta = encontrados.stream()
                .map( itinerarioService::toResponse )
                .toList();



        return ResponseEntity.ok( resposta );
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItinerarioResponse> findById(@PathVariable Long id) {

        var encontrado = itinerarioService.findById( id );

        if (Objects.isNull( encontrado )) return ResponseEntity.notFound().build();

        var resposta = itinerarioService.toResponse( encontrado );

        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<ItinerarioResponse> save(@RequestBody @Valid ItinerarioRequest r) {

        var entity = itinerarioService.toEntity(r);
        var saved = itinerarioService.save(entity);
        var response = itinerarioService.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
