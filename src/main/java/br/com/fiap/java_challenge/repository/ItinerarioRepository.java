package br.com.fiap.java_challenge.repository;

import br.com.fiap.java_challenge.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {


}
