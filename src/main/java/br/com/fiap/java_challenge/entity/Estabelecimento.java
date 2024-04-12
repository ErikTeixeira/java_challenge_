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
@Table(name = "TB_ESTABELECIMENTO")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESTABELECIMENTO")
    @SequenceGenerator(name = "SQ_ESTABELECIMENTO", sequenceName = "SQ_ESTABELECIMENTO", allocationSize = 1)
    @Column(name = "ID_ESTABELECIMENTO")
    private Long id;

    @Column(name = "NM_ESTABELECIMENTO")
    private String nome;

    @Column(name = "CEP_ESTABELECIMENTO")
    private String cep;

    private String tipo_estabelecimento;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_AVALIACAO_ESTABELECIMENTO",
            joinColumns = {
                    @JoinColumn(
                            name = "ESTABELECIMENTO",
                            referencedColumnName = "ID_ESTABELECIMENTO",
                            foreignKey = @ForeignKey(
                                    name = "FK_ESTABELECIMENTO_AVALIACAO"
                            )
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "AVALIACAO",
                            referencedColumnName = "ID_AVALIACAO",
                            foreignKey = @ForeignKey(
                                    name = "FK_AVALIACAO_ESTABELECIMENTO"
                            )
                    )
            }
    )
    private Set<Avaliacao> avaliacao = new LinkedHashSet<>();

}
