package br.usp.pcs.labsoft.salateca.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Turma;

public interface GerenciadorDeTurmas extends CrudRepository<Turma, Integer>{
    public Optional<Turma> findByCodigo(String codigo);
    List<Turma> findAll();
}