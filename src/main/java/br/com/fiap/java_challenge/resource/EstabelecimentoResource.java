package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.EstabelecimentoRequest;
import br.com.fiap.java_challenge.dto.response.EstabelecimentoResponse;
import br.com.fiap.java_challenge.entity.Estabelecimento;
import br.com.fiap.java_challenge.service.EstabelecimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoResource {

    @Autowired
    EstabelecimentoService service;

    @GetMapping
    public List<EstabelecimentoResponse> findAll(@PathVariable Long id ) {
        Estabelecimento estabelecimento = service.findById(id);
        return Collections.singletonList(service.toResponse(estabelecimento));
    }

    @GetMapping(value = "/{id}")
    public EstabelecimentoResponse findById(@PathVariable Long id) {
        Estabelecimento estabelecimento = service.findById(id);
        return service.toResponse((estabelecimento));
    }

    @Transactional
    @PostMapping
    public EstabelecimentoResponse save(@RequestBody @Valid EstabelecimentoRequest estabelecimento ) {
        if (Objects.isNull(estabelecimento)) return null;
        Estabelecimento save = service.save(service.toEntity(estabelecimento));
        return service.toResponse(save);
    }

}
