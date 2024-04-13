package br.com.fiap.java_challenge.resource;


import br.com.fiap.java_challenge.dto.request.ItinerarioRequest;
import br.com.fiap.java_challenge.dto.response.ItinerarioResponse;
import br.com.fiap.java_challenge.entity.Itinerario;
import br.com.fiap.java_challenge.service.ItinerarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/itinerario")
public class ItinerarioResource {

    @Autowired
    ItinerarioService service;

    @GetMapping
    public List<ItinerarioResponse> findAll(@PathVariable Long id ) {
        Itinerario itinerario = service.findById(id);
        return Collections.singletonList(service.toResponse(itinerario));
    }

    @GetMapping(value = "/{id}")
    public ItinerarioResponse findById(@PathVariable Long id) {
        Itinerario itinerario = service.findById(id);
        return service.toResponse((itinerario));
    }

    @Transactional
    @PostMapping
    public ItinerarioResponse save(@RequestBody @Valid ItinerarioRequest itinerario ) {
        if (Objects.isNull(itinerario)) return null;
        Itinerario save = service.save(service.toEntity(itinerario));
        return service.toResponse(save);
    }

}
