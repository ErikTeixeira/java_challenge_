package br.com.fiap.java_challenge.dto.response;

import br.com.fiap.java_challenge.entity.Itinerario;
import lombok.Builder;

import java.util.Collection;

@Builder
public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Integer idade,
        String genero,
        Collection<ItinerarioResponse> itinerario

) {
}
