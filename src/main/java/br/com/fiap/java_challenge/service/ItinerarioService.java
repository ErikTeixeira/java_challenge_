package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.ItinerarioRequest;
import br.com.fiap.java_challenge.dto.response.ItinerarioResponse;
import br.com.fiap.java_challenge.entity.Itinerario;
import br.com.fiap.java_challenge.repository.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItinerarioService implements ServiceDTO<Itinerario, ItinerarioRequest, ItinerarioResponse> {

    @Autowired
    private ItinerarioRepository repo;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Collection<Itinerario> findAll(Example<Itinerario> example) {
        return repo.findAll(example);
    }

    @Override
    public Itinerario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Itinerario save(Itinerario e) {
        return repo.save(e);
    }

    @Override
    public Itinerario toEntity(ItinerarioRequest dto) {

        var usuario = usuarioService.findById(dto.usuario().id());

        return Itinerario.builder()
                .descricao(dto.descricao())
                .usuario(usuario)
                .build();
    }

    @Override
    public ItinerarioResponse toResponse(Itinerario e) {

        var usuario = usuarioService.toResponse(e.getUsuario());

        return ItinerarioResponse.builder()
                .id(e.getId())
                .descricao(e.getDescricao())
                .usuario(usuario)
                .build();
    }
}
