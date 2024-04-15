package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record ItinerarioResponse(

        Long id,
        String descricao,
        Collection<UsuarioResponse> usuario

) {
}
