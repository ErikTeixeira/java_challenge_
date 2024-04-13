package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

@Builder
public record ItinerarioResponse(

        Long id,
        String descricao

) {
}
