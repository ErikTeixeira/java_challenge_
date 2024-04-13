package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.entity.Usuario;
import br.com.fiap.java_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse, AbstractRequest> {

    @Autowired
    ItinerarioService itinerarioService;

    @Autowired
    private UsuarioRepository repo;


    @Override
    public UsuarioResponse toResponse(Usuario u) {

        return new UsuarioResponse(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getIdade(),
                u.getGenero(),
                itinerarioService.toResponse(u.getItinerario()
                )
        );
    }

    @Override
    public Usuario toEntity(UsuarioRequest usuarioRequest) {
        return Usuario.builder()
                .nome(usuarioRequest.nome())
                .email(usuarioRequest.email())
                .idade(Math.toIntExact(usuarioRequest.idade()))
                .genero(usuarioRequest.genero())
                .build();
    }

    @Override
    public Collection<UsuarioResponse> toResponse(Collection<Usuario> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Usuario> findAll() {

        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {

        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario findByAbstractRequest(AbstractRequest a) {
        if (Objects.isNull(a)) return null;
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Salvando os relacionamentos de itinerários
        usuario.getItinerario().forEach(itinerario -> itinerario.getUsuarios().add(usuario));
        return repo.save(usuario);
    }
}
