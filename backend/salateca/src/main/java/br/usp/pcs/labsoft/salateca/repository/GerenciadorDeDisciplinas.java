package br.usp.pcs.labsoft.salateca.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;

public interface GerenciadorDeDisciplinas extends CrudRepository<Disciplina, Integer>{
    public Optional<Disciplina> findByCodigo(String codigo);
    public List<Disciplina> findByNome(String nome);
}