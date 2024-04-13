package br.com.fiap.java_challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "TB_ITINERARIO")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITINERARIO")
    @SequenceGenerator(name = "SQ_ITINERARIO", sequenceName = "SQ_ITINERARIO", allocationSize = 1)
    @Column(name = "ID_ITINERARIO")
    private Long id;

    private String descricao;


}
