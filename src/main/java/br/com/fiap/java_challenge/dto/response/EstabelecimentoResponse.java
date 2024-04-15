package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

@Builder
public record EstabelecimentoResponse(

        Long id,
        String nome,
        String cep,
        String tipo_estabelecimento
) {
}
