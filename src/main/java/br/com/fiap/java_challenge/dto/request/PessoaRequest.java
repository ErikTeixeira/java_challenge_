package br.com.fiap.java_challenge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PessoaRequest(

        @Size(min = 2, max = 100, message = "A quantidade de caracteres do nome deve estar entre 2 e 100")
        @NotNull(message = "O nome é campo obrigatório")
        String nome,

        @Size(min = 2, max = 255, message = "A quantidade de caracteres do sobrenome deve estar entre 2 e 255")
        @NotNull(message = "O sobrenome é campo obrigatório")
        String sobrenome,

        @PastOrPresent(message = "Não aceitamos data no futuro")
        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        @Email(message = "Email é inválido")
        @NotNull(message = "Email é campo obrigatório")
        String email

) {
}
