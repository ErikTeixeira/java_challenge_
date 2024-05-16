package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.UsuarioRequest;
import br.com.fiap.java_challenge.dto.response.UsuarioResponse;
import br.com.fiap.java_challenge.entity.Usuario;
import br.com.fiap.java_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private PreferenciaViagemService preferenciaViagemService;


    @Override
    public Collection<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll(example);
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save(e);
    }

    @Override
    public Usuario toEntity(UsuarioRequest dto) {

        var pessoa = pessoaService.findById(dto.pessoa().id());
        var preferenciaViagem = preferenciaViagemService.findById(dto.preferenciaViagem().id);

        return Usuario.builder()
                .username(dto.username())
                .password(dto.password())
                .pessoa(pessoa)
                .preferenciaViagem(preferenciaViagem)
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {

        var pessoa = pessoaService.toResponse(e.getPessoa());
        var preferenciaViagem = preferenciaViagemService.toResponse(e.getPreferenciaViagem());

        Collection<UsuarioResponse> estabelecimentos = null;

        if (Objects.nonNull(e.getEstabelecimentos()) && !e.getEstabelecimentos().isEmpty())
            estabelecimentos = e.getEstabelecimentos().stream().map(estabelecimentoService::toResponse).toList();

        return UsuarioResponse.builder()
                .id(e.getId())
                .username(e.getUsername())
                .password(e.getPassword())
                .pessoa(pessoa)
                .estabelecimento(estabelecimentos)
                .preferenciaViagem(preferenciaViagem)
                .build();
    }
}
