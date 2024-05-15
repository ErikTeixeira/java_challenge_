package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EstabelecimentoRequest(

        @NotNull(message = "O nome do estabelecimento não pode ser nulo ")
        String nome,

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        @NotNull(message = "O CEP do estabelecimento deve ser informado")
        String cep,

        @Size(min = 5, max = 255)
        @NotNull(message = "O tipo de estabelecimento é obrigatório")
        String tipo_estabelecimento

) {
}
