package br.usp.pcs.labsoft.salateca.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Sala;

public interface SalaRepository extends CrudRepository<Sala, String> {
    // Método adicional para buscar por nome, se relevante
    Optional<Sala> findByNome(String nome);

    // Método adicional para buscar todas as salas
    List<Sala> findAll();
}
