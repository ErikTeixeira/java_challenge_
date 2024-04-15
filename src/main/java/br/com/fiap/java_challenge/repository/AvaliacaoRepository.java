package br.com.fiap.java_challenge.repository;

import br.com.fiap.java_challenge.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByEstabelecimentoId(Long id);

}
