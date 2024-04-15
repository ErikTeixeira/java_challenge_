package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.EnderecoRequest;
import br.com.fiap.java_challenge.dto.response.EnderecoResponse;
import br.com.fiap.java_challenge.entity.Endereco;
import br.com.fiap.java_challenge.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EnderecoService implements ServiceDTO<Endereco, EnderecoRequest, EnderecoResponse, AbstractRequest> {

    @Autowired
    private EnderecoRepository repo;


    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Endereco toEntity(EnderecoRequest enderecoRequest) {

        return Endereco.builder()
                .cep(enderecoRequest.cep())
                .numero(enderecoRequest.numero())
                .complemento(enderecoRequest.complemento())
                .build();
    }

    @Override
    public EnderecoResponse toResponse(Endereco endereco) {

        var usuario = usuarioService.toResponse(endereco.getUsuario());

        return EnderecoResponse.builder()
                .usuario(usuario)
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .id(endereco.getId())
                .build();
    }

    @Override
    public Collection<EnderecoResponse> toResponse(Collection<Endereco> entity) {
        if (entity == null) {
            return Collections.emptyList();
        }
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<Endereco> findAll() {
        return repo.findAll();
    }

    @Override
    public Endereco findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Endereco findByAbstractRequest(AbstractRequest a) {
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Endereco save(Endereco endereco) {
        return repo.save(endereco);
    }

    public List<Endereco> findByUsuarioId(Long id) {
        return repo.findByUsuarioId(id);
    }

}
