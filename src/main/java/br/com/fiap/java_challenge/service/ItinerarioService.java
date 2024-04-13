package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.ItinerarioRequest;
import br.com.fiap.java_challenge.dto.response.ItinerarioResponse;
import br.com.fiap.java_challenge.entity.Itinerario;
import br.com.fiap.java_challenge.repository.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;


@Service
public class ItinerarioService implements ServiceDTO<Itinerario, ItinerarioRequest, ItinerarioResponse, AbstractRequest> {

    @Autowired
    UsuarioService usuarioService;


    @Autowired
    private ItinerarioRepository repo;


    @Override
    public ItinerarioResponse toResponse(Itinerario i) {


        return new ItinerarioResponse(
                i.getId(),
                i.getDescricao(),
                usuarioService.toResponse(i.getUsuarios()
                )
        );
    }

    @Override
    public Itinerario toEntity(ItinerarioRequest itinerarioRequest) {
        return Itinerario.builder()
                .descricao(itinerarioRequest.descricao())
                .build();
    }


    @Override
    public Collection<ItinerarioResponse> toResponse(Collection<Itinerario> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Itinerario> findAll() {
        return repo.findAll();
    }

    @Override
    public Itinerario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Itinerario findByAbstractRequest(AbstractRequest a) {
        if (Objects.isNull(a)) return null;
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Itinerario save(Itinerario itinerario) {
        return repo.save(itinerario);
    }
}