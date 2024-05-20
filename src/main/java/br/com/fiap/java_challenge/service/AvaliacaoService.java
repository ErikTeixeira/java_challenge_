package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AvaliacaoRequest;
import br.com.fiap.java_challenge.dto.response.AvaliacaoResponse;
import br.com.fiap.java_challenge.entity.Avaliacao;
import br.com.fiap.java_challenge.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AvaliacaoService implements ServiceDTO<Avaliacao, AvaliacaoRequest, AvaliacaoResponse> {

    @Autowired
    private AvaliacaoRepository repo;

    @Autowired
    private EstabelecimentoService estabelecimentoService;


    @Override
    public Collection<Avaliacao> findAll(Example<Avaliacao> example) {
        return repo.findAll(example);
    }

    @Override
    public Avaliacao findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Avaliacao save(Avaliacao e) {
        return repo.save(e);
    }

    @Override
    public Avaliacao toEntity(AvaliacaoRequest dto) {

        var estabelecimento = estabelecimentoService.findById(dto.estabelecimento().id());

        return Avaliacao.builder()
                .comentario(dto.comentario())
                .nota(dto.nota())
                .dataAvaliacao(dto.dataAvaliacao())
                .estabelecimento(estabelecimento)
                .build();
    }

    @Override
    public AvaliacaoResponse toResponse(Avaliacao e) {

        var estabelecimento = estabelecimentoService.toResponse(e.getEstabelecimento());

        return AvaliacaoResponse.builder()
                .id(e.getId())
                .comentario(e.getComentario())
                .nota(e.getNota())
                .dataAvaliacao(e.getDataAvaliacao())
                .estabelecimento(estabelecimento)
                .build();
    }
}
