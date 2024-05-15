package br.com.fiap.java_challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEstabelecimento {

    HOTEL("Hotel"),
    RESTAURANTE("Restaurante"),
    PONTO_TURISTICO("Ponto Turístico"),
    PARQUE("Parque"),
    MUSEU("Museu"),
    TEATRO("Teatro");


    private final String tipo;
}
