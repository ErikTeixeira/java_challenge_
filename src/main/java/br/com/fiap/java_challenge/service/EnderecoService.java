package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.EnderecoRequest;
import br.com.fiap.java_challenge.dto.response.EnderecoResponse;
import br.com.fiap.java_challenge.entity.Endereco;
import br.com.fiap.java_challenge.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnderecoService implements ServiceDTO<Endereco, EnderecoRequest, EnderecoResponse> {

    @Autowired
    private EnderecoRepository repo;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Collection<Endereco> findAll(Example<Endereco> example) {
        return repo.findAll(example);
    }

    @Override
    public Endereco findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Endereco save(Endereco e) {
        return repo.save(e);
    }

    @Override
    public Endereco toEntity(EnderecoRequest dto) {

        var usuario = usuarioService.findById(dto.usuario().id());

        return Endereco.builder()
                .cep(dto.cep())
                .numero(dto.numero())
                .complemento(dto.complemento())
                .usuario(usuario)
                .build();
    }

    @Override
    public EnderecoResponse toResponse(Endereco e) {

        var usuario = usuarioService.toResponse(e.getUsuario());

        return EnderecoResponse.builder()
                .id(e.getId())
                .cep(e.getCep())
                .numero(e.getNumero())
                .complemento(e.getComplemento())
                .usuario(usuario)
                .build();
    }
}
