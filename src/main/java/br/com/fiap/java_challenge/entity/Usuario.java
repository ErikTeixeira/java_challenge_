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
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NM_USUARIO")
    private String nome;

    private String email;

    private Integer idade;

    private String genero;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_ITINERARIO_USUARIO",
            joinColumns = {
                    @JoinColumn(
                            name = "USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_USUARIO_ITINERARIO"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ITINERARIO",
                            referencedColumnName = "ID_ITINERARIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_ITINERARIO_USUARIO"
                            )
                    )
            }
    )
    private Set<Itinerario> itinerario = new LinkedHashSet<>();



}
