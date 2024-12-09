package br.usp.pcs.labsoft.salateca.repository;

import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Atividade;
import java.util.List;
import java.util.Optional;

public interface GerenciadorDeAtividades extends CrudRepository<Atividade, Integer> {
    Optional<Atividade> findByCodigo(String codigo);
    List<Atividade> findAll();
}
