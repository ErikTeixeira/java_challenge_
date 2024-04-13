package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.PreferenciaViagemRequest;
import br.com.fiap.java_challenge.dto.response.PreferenciaViagemResponse;
import br.com.fiap.java_challenge.entity.PreferenciaViagem;
import br.com.fiap.java_challenge.service.PreferenciaViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/preferencia_viagem")
public class PreferenciaViagemResource {

    @Autowired
    PreferenciaViagemService service;

    @GetMapping
    public List<PreferenciaViagemResponse> findAll(@PathVariable Long id ) {
        PreferenciaViagem preferenciaViagem = service.findById(id);
        return Collections.singletonList(service.toResponse(preferenciaViagem));
    }

    @GetMapping(value = "/{id}")
    public PreferenciaViagemResponse findById(@PathVariable Long id) {
        PreferenciaViagem preferenciaViagem = service.findById(id);
        return service.toResponse((preferenciaViagem));
    }

    @Transactional
    @PostMapping
    public PreferenciaViagemResponse save(@RequestBody @Valid PreferenciaViagemRequest preferenciaViagem ) {
        if (Objects.isNull(preferenciaViagem)) return null;
        PreferenciaViagem save = service.save(service.toEntity(preferenciaViagem));
        return service.toResponse(save);
    }

}
