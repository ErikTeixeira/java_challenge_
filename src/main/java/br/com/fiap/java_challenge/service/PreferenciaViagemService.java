package br.com.fiap.java_challenge.service;

import br.com.fiap.java_challenge.dto.request.AbstractRequest;
import br.com.fiap.java_challenge.dto.request.PreferenciaViagemRequest;
import br.com.fiap.java_challenge.dto.response.PreferenciaViagemResponse;
import br.com.fiap.java_challenge.entity.PreferenciaViagem;
import br.com.fiap.java_challenge.repository.PreferenciaViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PreferenciaViagemService implements ServiceDTO<PreferenciaViagem, PreferenciaViagemRequest, PreferenciaViagemResponse, AbstractRequest> {

    @Autowired
    private PreferenciaViagemRepository repo;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public PreferenciaViagemResponse toResponse(PreferenciaViagem preferenciaViagem) {

        var usuario = usuarioService.toResponse(preferenciaViagem.getUsuario());

        return PreferenciaViagemResponse.builder()
                .id(preferenciaViagem.getId())
                .tipo_culinaria(preferenciaViagem.getTipo_culinaria())
                .restricoes_alimentares(preferenciaViagem.getRestricoes_alimentares())
                .tipo_transporte(preferenciaViagem.getTipo_transporte())
                .tipo_hospedagem(preferenciaViagem.getTipo_hospedagem())
                .viaja_sozinho(preferenciaViagem.getViaja_sozinho())
                .usuario(usuario)
                .build();
    }

    @Override
    public PreferenciaViagem toEntity(PreferenciaViagemRequest preferenciaViagemRequest) {
        return PreferenciaViagem.builder()
                .tipo_culinaria(preferenciaViagemRequest.tipo_culinaria())
                .restricoes_alimentares(preferenciaViagemRequest.restricoes_alimentares())
                .tipo_transporte(preferenciaViagemRequest.tipo_transporte())
                .tipo_hospedagem(preferenciaViagemRequest.tipo_hospedagem())
                .viaja_sozinho(preferenciaViagemRequest.viaja_sozinho())
                .build();
    }

    @Override
    public Collection<PreferenciaViagemResponse> toResponse(Collection<PreferenciaViagem> entity) {
        return entity.stream().map(this::toResponse).toList();
    }

    @Override
    public Collection<PreferenciaViagem> findAll() {
        return repo.findAll();
    }

    @Override
    public PreferenciaViagem findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public PreferenciaViagem findByAbstractRequest(AbstractRequest a) {

        return repo.findById(a.id()).orElse(null);
    }

    @Override
    public PreferenciaViagem save(PreferenciaViagem telefone) {

        return repo.save(telefone);
    }

    public List<PreferenciaViagem> findByUsuarioId(Long id) {

        return  repo.findByUsuarioId(id);
    }
}
