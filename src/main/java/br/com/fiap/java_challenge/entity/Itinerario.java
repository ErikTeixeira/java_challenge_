package br.com.fiap.java_challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO_ID",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(
                    name = "FK_ITINERARIO_USUARIO"
            )
    )
    private Usuario usuario;

}
