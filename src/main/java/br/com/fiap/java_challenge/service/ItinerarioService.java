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
import java.util.stream.Collectors;

@Service
public class ItinerarioService implements ServiceDTO<Itinerario, ItinerarioRequest, ItinerarioResponse, AbstractRequest> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItinerarioRepository repo;

    @Override
    public ItinerarioResponse toResponse(Itinerario itinerario) {

        var usuario = usuarioService.toResponse(itinerario.getUsuarios());

        return ItinerarioResponse.builder()
                .usuario(usuario)
                .descricao(itinerario.getDescricao())
                .build();
    }

    @Override
    public Itinerario toEntity(ItinerarioRequest itinerarioRequest) {
        return Itinerario.builder()
                .descricao(itinerarioRequest.descricao())
                .build();
    }

    @Override
    public Collection<ItinerarioResponse> toResponse(Collection<Itinerario> itinerarios) {
        return itinerarios.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
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
        if (Objects.isNull(a) || Objects.isNull(a.id())) return null;
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Itinerario save(Itinerario itinerario) {
        return repo.save(itinerario);
    }


}
