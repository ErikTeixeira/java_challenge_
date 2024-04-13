package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.AvaliacaoRequest;
import br.com.fiap.java_challenge.dto.response.AvaliacaoResponse;
import br.com.fiap.java_challenge.entity.Avaliacao;
import br.com.fiap.java_challenge.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService implements ServiceDTO<Avaliacao, AvaliacaoRequest, AvaliacaoResponse, AbstractRequest> {

    @Autowired
    AvaliacaoRepository repo;

    @Override
    public Avaliacao toEntity(AvaliacaoRequest avaliacaoRequest) {
        return Avaliacao.builder()
                .comentario(avaliacaoRequest.comentario())
                .nota(avaliacaoRequest.nota())
                .build();
    }

    @Override
    public AvaliacaoResponse toResponse(Avaliacao avaliacao) {
        return new AvaliacaoResponse(avaliacao.getId(), avaliacao.getComentario(), avaliacao.getNota());
    }

    @Override
    public Collection<AvaliacaoResponse> toResponse(Collection<Avaliacao> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Avaliacao> findAll() {
        return repo.findAll();
    }

    @Override
    public Avaliacao findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Avaliacao findByAbstractRequest(AbstractRequest a) {
        if (Objects.isNull(a) || Objects.isNull(a.id())) return null;
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao)
    {
        return repo.save(avaliacao);
    }
}
