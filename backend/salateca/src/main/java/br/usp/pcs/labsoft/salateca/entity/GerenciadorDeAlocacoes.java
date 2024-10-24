package br.usp.pcs.labsoft.salateca.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.usp.pcs.labsoft.salateca.entity.Turma;

@Repository
public class GerenciadorDeAlocacoes {

  public Collection<Turma> getTurmas(int idDisciplina){
      ArrayList<Turma> turmas = new ArrayList<>();

      Turma turma1 = new Turma("2024001", "1");
      Turma turma2 = new Turma("2024002", "2");
      Turma turma3 = new Turma("2024003", "3");
      Turma turma4 = new Turma("2024004", "4");
      
      turmas.add(turma1);
      turmas.add(turma2);
      turmas.add(turma3);
      turmas.add(turma4);
      
      return turmas;

  }
}