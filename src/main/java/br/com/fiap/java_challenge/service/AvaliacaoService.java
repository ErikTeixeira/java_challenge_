package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.AvaliacaoRequest;
import br.com.fiap.java_challenge.dto.response.AvaliacaoResponse;
import br.com.fiap.java_challenge.entity.Avaliacao;
import br.com.fiap.java_challenge.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AvaliacaoService implements ServiceDTO<Avaliacao, AvaliacaoRequest, AvaliacaoResponse, AbstractRequest> {

    @Autowired
    private AvaliacaoRepository repo;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Override
    public Avaliacao toEntity(AvaliacaoRequest avaliacaoRequest) {
        return Avaliacao.builder()
                .comentario(avaliacaoRequest.comentario())
                .nota(avaliacaoRequest.nota())
                .build();
    }

    @Override
    public AvaliacaoResponse toResponse(Avaliacao avaliacao) {

        var estabelecimento = estabelecimentoService.toResponse(avaliacao.getEstabelecimento());

        return AvaliacaoResponse.builder()
                .estabelecimento(estabelecimento)
                .id(avaliacao.getId())
                .comentario(avaliacao.getComentario())
                .nota(avaliacao.getNota())
                .build();
    }

    @Override
    public Collection<AvaliacaoResponse> toResponse(Collection<Avaliacao> entity) {
        if (entity == null) {
            return Collections.emptyList();
        }
        return entity.stream().map(this::toResponse).toList();
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
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        return repo.save(avaliacao);
    }

    public List<Avaliacao> findByEstabelecimentoId(Long id) {
        return repo.findByEstabelecimentoId(id);
    }
}
