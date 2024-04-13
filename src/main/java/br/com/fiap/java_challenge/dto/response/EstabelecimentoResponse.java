package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record EstabelecimentoResponse(

        Long id,
        String nome,
        String cep,
        String tipo_estabelecimento,
        Collection<AvaliacaoResponse> avaliacao
) {
}
