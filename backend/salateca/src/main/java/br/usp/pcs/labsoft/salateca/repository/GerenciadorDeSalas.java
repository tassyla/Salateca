package br.usp.pcs.labsoft.salateca.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Sala;

public interface GerenciadorDeSalas extends CrudRepository<Sala, String> {
    Optional<Sala> findByNome(String nome);
    List<Sala> findAll();
}
