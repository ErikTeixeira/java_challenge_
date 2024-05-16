package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PreferenciaViagemRequest(

        @NotNull(message = "O tipo de culinária que o usuário gosta é um campo obrigatório")
        String tipo_culinaria,

        @NotNull(message = "Responder se o usuário possui ou não restrições alimentares é obrigatório")
        String restricoes_alimentares,

        @NotNull(message = "O tipo de transporte que o usuário quer usar é um campo obrigatório")
        String tipo_transporte,

        @NotNull(message = "O tipo de hospedagem que o usuário quer é um campo obrigatório")
        String tipo_hospedagem,

        @NotNull(message = "É obrigatório este campo se o usuário irá viajar sozinho ou não")
        String viaja_sozinho


) {
}
