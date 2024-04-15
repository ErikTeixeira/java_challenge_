package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.entity.Usuario;
import br.com.fiap.java_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse, AbstractRequest> {

    @Autowired
    private PreferenciaViagemService preferenciaViagemService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private UsuarioRepository repo;


    @Override
    public UsuarioResponse toResponse(Usuario u) {

        var estabelecimento = estabelecimentoService.toResponse(u.getEstabelecimentos());
        var prefereciaViagem = preferenciaViagemService.toResponse(u.getPreferenciaViagem());

        return UsuarioResponse.builder()
                .estabelecimento(estabelecimento)
                .preferenciaViagem(prefereciaViagem)
                .nome(u.getNome())
                .email(u.getEmail())
                .idade(u.getIdade())
                .genero(u.getGenero())
                .build();
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
        if (entity == null) {
            return Collections.emptyList();
        }
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
    public Usuario save(Usuario usuario)
    {
        return repo.save(usuario);
    }


    public List<Usuario> findByPreferenciaViagemId(Long id) {
        return repo.findByPreferenciaViagemId(id);
    }
}
