package br.com.fiap.java_challenge.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record UsuarioResponse(
        Long id,
        String username,
        String password,
        PessoaResponse pessoa,

        Collection<EstabelecimentoResponse> estabelecimento,

        PreferenciaViagemResponse preferenciaViagem

) {
}
