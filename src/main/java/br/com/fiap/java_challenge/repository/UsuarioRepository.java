package br.com.fiap.java_challenge.repository;

import br.com.fiap.java_challenge.entity.Avaliacao;
import br.com.fiap.java_challenge.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByPreferenciaViagemId(Long id);

}
