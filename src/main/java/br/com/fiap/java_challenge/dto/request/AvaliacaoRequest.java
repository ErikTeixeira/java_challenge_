package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AvaliacaoRequest(

        @NotNull(message = "O comentário não pode ser nulo")
        String comentario,

        @Min(value = 1, message = "A nota deve ser no mínimo 1")
        @Max(value = 5, message = "A nota deve ser no máximo 5")
        @NotNull(message = "A nota não pode ser nula, e tem que ser de 1 a 5")
        Long nota,

        @PastOrPresent(message = "Não aceitamos data no futuro")
        LocalDate dataAvaliacao,

        @Valid
        @NotNull(message = "É necessário informar os dados do estabelecimento")
        AbstractRequest estabelecimento

) {

}
