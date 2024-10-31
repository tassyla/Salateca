package br.usp.pcs.labsoft.salateca;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.usp.pcs.labsoft.salateca.entity.Alocacao;
import br.usp.pcs.labsoft.salateca.entity.Turma;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeTurmas;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;

@Configuration public class DataLoader {
  private GerenciadorDeTurmas turmas;
  private GerenciadorDeDisciplinas disciplinas;

  DataLoader(GerenciadorDeTurmas turmas, GerenciadorDeDisciplinas disciplinas) {
    this.turmas = turmas;
    this.disciplinas = disciplinas;
  };

  @Bean public ApplicationRunner carregaDisciplinas() {
    return args-> {
      disciplinas.save(new Disciplina("PCS1234", "Laboratório de Gatinhos"));
      disciplinas.save(new Disciplina("PTC5678", "Sistemas de DP"));
      disciplinas.save(new Disciplina("PEA4567", "Margaridas II"));
    };
  }
}
