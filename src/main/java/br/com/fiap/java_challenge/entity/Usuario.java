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
            name = "TB_ESTABELECIMENTO_USUARIO",
            joinColumns = {
                    @JoinColumn(
                            name = "USUARIO",
                            referencedColumnName = "ID_USUARIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_USUARIO_ESTABELECIMENTO"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ESTABELECIMENTO",
                            referencedColumnName = "ID_ESTABELECIMENTO",
                            foreignKey = @ForeignKey(
                                    name = "FK_ESTABELECIMENTO_USUARIO"
                            )
                    )
            }
    )
    private Set<Estabelecimento> estabelecimentos = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PREFERENCIA_VIAGEM",
            referencedColumnName = "ID_PREFERENCIAVIAGEM",
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_PREFERENCIA_VIAGEM"
            )
    )
    private PreferenciaViagem preferenciaViagem;



}
