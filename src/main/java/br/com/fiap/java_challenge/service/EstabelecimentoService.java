package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.EstabelecimentoRequest;
import br.com.fiap.java_challenge.dto.response.EstabelecimentoResponse;
import br.com.fiap.java_challenge.entity.Estabelecimento;
import br.com.fiap.java_challenge.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class EstabelecimentoService implements ServiceDTO<Estabelecimento, EstabelecimentoRequest, EstabelecimentoResponse, AbstractRequest> {

    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    private EstabelecimentoRepository repo;

    @Override
    public EstabelecimentoResponse toResponse(Estabelecimento e) {
        return new EstabelecimentoResponse(
                e.getId(),
                e.getNome(),
                e.getCep(),
                e.getTipo_estabelecimento(),
                avaliacaoService.toResponse(e.getAvaliacao())
        );
    }

    @Override
    public Estabelecimento toEntity(EstabelecimentoRequest estabelecimentoRequest) {
        return Estabelecimento.builder()
                .nome(estabelecimentoRequest.nome())
                .cep(estabelecimentoRequest.cep())
                .tipo_estabelecimento(estabelecimentoRequest.tipo_estabelecimento())
                .build();
    }

    @Override
    public Collection<EstabelecimentoResponse> toResponse(Collection<Estabelecimento> entity) {
        return entity.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Collection<Estabelecimento> findAll() {
        return repo.findAll();
    }

    @Override
    public Estabelecimento findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Estabelecimento findByAbstractRequest(AbstractRequest a) {
        if (Objects.isNull(a)) return null;
        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public Estabelecimento save(Estabelecimento estabelecimento) {
        return repo.save(estabelecimento);
    }
}
