package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest(

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        @NotNull(message = "O CEP do uusuário deve ser informado")
        String cep,
        String numero,
        String complemento

) {
}
