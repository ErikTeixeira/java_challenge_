package br.com.fiap.java_challenge.dto.request;

import br.com.fiap.java_challenge.entity.TipoEstabelecimento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EstabelecimentoRequest(

        @Size(min = 2, max = 200, message = "A quantidade de caracteres do nome deve estar entre 2 e 200")
        @NotNull(message = "O nome do estabelecimento não pode ser nulo ")
        String nome,

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        @NotNull(message = "O CEP do estabelecimento deve ser informado")
        String cep,

        @NotNull(message = "O tipo de estabelecimento é obrigatório, sendo eles RESTAURANTE -> R, PONTO TURISTICO -> PT, PARQUE -> P, MUSEU -> M, TEATRO -> T")
        TipoEstabelecimento tipo

) {
}
