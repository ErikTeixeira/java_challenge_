package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.EnderecoRequest;
import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.EnderecoResponse;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.service.EnderecoService;
import br.com.fiap.java_challenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService service;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return service.findAll().stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest usuario) {
        var entity = service.toEntity(usuario);
        var salvo = service.save(entity);
        var response = service.toResponse(salvo);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);

    }

    @Transactional
    @PostMapping(value = "/{id}/endereco")
    public ResponseEntity<EnderecoResponse> save(@PathVariable Long id, @RequestBody @Valid EnderecoRequest endereco) {

        var usuario = service.findById(id);

        if (Objects.isNull(endereco)) return ResponseEntity.badRequest().build();

        var entity = enderecoService.toEntity(endereco);

        entity.setUsuario(usuario);

        var saved = enderecoService.save(entity);
        var response = enderecoService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}/endereco")
    public ResponseEntity<Collection<EnderecoResponse>> findEnderecoByUsuario(@PathVariable Long id) {
        var endereco = enderecoService.findByUsuarioId(id);
        var response = enderecoService.toResponse(endereco);
        return ResponseEntity.ok(response);
    }



}
