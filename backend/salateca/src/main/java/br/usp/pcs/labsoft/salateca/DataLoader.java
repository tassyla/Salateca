package br.usp.pcs.labsoft.salateca;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.usp.pcs.labsoft.salateca.entity.Alocacao;
import br.usp.pcs.labsoft.salateca.entity.Turma;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeSalas;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;

@Configuration
public class DataLoader {

    private final GerenciadorDeDisciplinas disciplinas;
    private final GerenciadorDeSalas salas;

    public DataLoader(GerenciadorDeDisciplinas disciplinas, GerenciadorDeSalas salas) {
        this.disciplinas = disciplinas;
        this.salas = salas;
    }

    @Bean
    public ApplicationRunner carregarDados() {
        return args -> {
            // Apagar todos os dados
            disciplinas.deleteAll();
            salas.deleteAll();

            // Criar disciplinas e associar turmas
            Disciplina d1 = new Disciplina("PCS1234", "Laboratório de Gatinhos");
            Disciplina d2 = new Disciplina("PTC5678", "Sistemas de DP");
            Disciplina d3 = new Disciplina("PEA4567", "Margaridas II");

            Turma t1 = new Turma("T01", 30, true);  // Requer computador
            Turma t2 = new Turma("T02", 25, false); // Não requer computador
            Turma t3 = new Turma("T03", 20, false); // Não requer computador

            d1.adicionaTurma(t1);
            d2.adicionaTurma(t2);
            d3.adicionaTurma(t3);

            disciplinas.save(d1);
            disciplinas.save(d2);
            disciplinas.save(d3);

            // Criar alocações
            Alocacao a1 = new Alocacao("T01", "Laboratório A", "Segunda-feira", "08:00", "10:00", true); // Sala com computadores
            Alocacao a2 = new Alocacao("T02", "Sala 101", "Quarta-feira", "14:00", "16:00", false);      // Sala sem computadores
            Alocacao a3 = new Alocacao("T03", "Laboratório B", "Sexta-feira", "10:00", "12:00", true);   // Sala com computadores

            t1.adicionaAlocacao(a1);
            t2.adicionaAlocacao(a2);
            t3.adicionaAlocacao(a3);

            // Atualizar disciplinas no banco (com suas turmas e alocações)
            disciplinas.save(d1);
            disciplinas.save(d2);
            disciplinas.save(d3);
        };
    }
}
