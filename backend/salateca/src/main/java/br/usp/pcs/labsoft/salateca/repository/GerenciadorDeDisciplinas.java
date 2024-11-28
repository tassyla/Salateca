package br.usp.pcs.labsoft.salateca.repository;

import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import java.util.List;
import java.util.Optional;

public interface GerenciadorDeDisciplinas extends CrudRepository<Disciplina, Integer> {
    Optional<Disciplina> findByCodigo(String codigo);
    List<Disciplina> findAll();
}
