package br.com.fiap.java_challenge.resource;

import br.com.fiap.java_challenge.dto.request.AvaliacaoRequest;
import br.com.fiap.java_challenge.dto.request.EstabelecimentoRequest;
import br.com.fiap.java_challenge.dto.response.AvaliacaoResponse;
import br.com.fiap.java_challenge.dto.response.EstabelecimentoResponse;
import br.com.fiap.java_challenge.service.AvaliacaoService;
import br.com.fiap.java_challenge.service.EstabelecimentoService;
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
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoResource {

    @Autowired
    EstabelecimentoService service;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<EstabelecimentoResponse> findAll() {
        return service.findAll().stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public EstabelecimentoResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EstabelecimentoResponse> save(@RequestBody @Valid EstabelecimentoRequest estabelecimento) {
        var entity = service.toEntity(estabelecimento);
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
    @PostMapping(value = "/{id}/avaliacao")
    public ResponseEntity<AvaliacaoResponse> save(@PathVariable Long id, @RequestBody @Valid AvaliacaoRequest avaliacao) {

        var estabelecimento = service.findById(id);

        if (Objects.isNull(avaliacao)) return ResponseEntity.badRequest().build();

        var entity = avaliacaoService.toEntity(avaliacao);

        entity.setEstabelecimento(estabelecimento);

        var saved = avaliacaoService.save(entity);
        var response = avaliacaoService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @GetMapping(value = "/{id}/avaliacao")
    public ResponseEntity<Collection<AvaliacaoResponse>> findAvaliacaoByEstabelecimento(@PathVariable Long id) {
        var estabelecimnto = avaliacaoService.findByEstabelecimentoId(id);
        var response = avaliacaoService.toResponse(estabelecimnto);
        return ResponseEntity.ok(response);
    }

}
