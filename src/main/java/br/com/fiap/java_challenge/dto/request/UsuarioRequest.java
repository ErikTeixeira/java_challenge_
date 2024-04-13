package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(

        @NotNull(message = "O nome do usuário não pode ser nulo")
        String nome,

        @NotNull(message = "O email do usuário não pode ser nulo")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "O email do técnico deve ter um formato válido")
        String email,

        @NotNull(message = "A idade do usuário não pode ser nula")
        Long idade,

        @NotNull(message = "O gênero do usuário não pode ser nulo")
        String genero


) {
}
