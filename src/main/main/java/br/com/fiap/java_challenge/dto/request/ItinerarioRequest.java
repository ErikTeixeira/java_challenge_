package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record ItinerarioRequest(

        @NotNull(message = "Deve ter a descrição do itinerário")
        String descricao

) {
}
