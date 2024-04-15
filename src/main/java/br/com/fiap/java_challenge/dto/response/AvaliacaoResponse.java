package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

@Builder
public record AvaliacaoResponse(

        Long id,
        String comentario,
        Long nota,

        EstabelecimentoResponse estabelecimento
) {
}
