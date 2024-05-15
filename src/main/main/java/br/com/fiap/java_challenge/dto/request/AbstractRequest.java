package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record AbstractRequest(

        @NotNull(message = "O id deve ser informado")
        Long id

) {
}
