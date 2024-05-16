package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

@Builder
public record EnderecoResponse(

        Long id,
        String cep,
        String numero,
        String complemento,
        UsuarioResponse usuario

) {
}
