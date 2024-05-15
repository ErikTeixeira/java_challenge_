package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequest(

        @NotNull(message = "O comentário não pode ser nulo")
        String comentario,

        @Min(value = 1, message = "A nota deve ser no mínimo 1")
        @Max(value = 5, message = "A nota deve ser no máximo 5")
        @NotNull(message = "A nota não pode ser nula, e tem que ser de 1 a 5")
        Long nota

) {

}
