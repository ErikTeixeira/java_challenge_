package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.entity.Usuario;
import br.com.fiap.java_challenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService service;

    @GetMapping
    public List<UsuarioResponse> findAll(@PathVariable Long id ) {
        Usuario usuario = service.findById(id);
        return Collections.singletonList(service.toResponse(usuario));
    }

    @GetMapping(value = "/{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        return service.toResponse((usuario));
    }

    @Transactional
    @PostMapping
    public UsuarioResponse save(@RequestBody @Valid UsuarioRequest usuario ) {
        if (Objects.isNull(usuario)) return null;
        Usuario save = service.save(service.toEntity(usuario));
        return service.toResponse(save);
    }

}
