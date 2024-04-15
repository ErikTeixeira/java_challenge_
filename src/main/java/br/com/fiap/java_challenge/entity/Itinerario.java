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


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_USUARIO_ITINERARIO",
            joinColumns = {
                    @JoinColumn(
                            name = "ITINERARIO",
                            referencedColumnName = "ID_ITINERARIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_ITINERARIO_USUARIO"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_USUARIO_ITINERARIO"
                            )
                    )
            }
    )
    private Set<Usuario> usuarios = new LinkedHashSet<>();


}
