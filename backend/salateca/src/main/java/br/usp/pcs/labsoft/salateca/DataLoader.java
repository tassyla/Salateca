package br.usp.pcs.labsoft.salateca;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.usp.pcs.labsoft.salateca.entity.Atividade;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;
import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;



@Configuration
public class DataLoader {

    private final GerenciadorDeDisciplinas disciplinas;
    private final GerenciadorDeSala salas;

    public DataLoader(GerenciadorDeDisciplinas disciplinas, GerenciadorDeSala salas) {
        this.disciplinas = disciplinas;
        this.salas = salas;
    }

    @Bean
    public ApplicationRunner carregarDados() {
        return args -> {
            // Apagar todos os dados
            disciplinas.deleteAll();

            // Criando listas de informacoes para turmas
            // Lista de dias da semana
            List<String> dias_d1_t1 = new ArrayList<>();
            dias_d1_t1.add("Segunda-feira");
            dias_d1_t1.add("Terça-feira");

            List<String> dias_d1_t2 = new ArrayList<>();
            dias_d1_t2.add("Quarta-feira");
            dias_d1_t2.add("Quinta-feira");

            List<String> dias_d1_t3 = new ArrayList<>();
            dias_d1_t3.add("Sexta-feira");
            dias_d1_t3.add("Segunda-feira");

            List<String> dias_d2_t1 = new ArrayList<>();
            dias_d2_t1.add("Terça-feira");

            List<String> dias_d2_t2 = new ArrayList<>();
            dias_d2_t2.add("Quarta-feira");

            List<String> dias_d3_t1 = new ArrayList<>();
            dias_d3_t1.add("Quinta-feira");
            dias_d3_t1.add("Sexta-feira");
            dias_d3_t1.add("Segunda-feira");
            
            // Lista de horários de inicio
            List<LocalTime> horarios_inicio_d1_t1 = new ArrayList<>();
            horarios_inicio_d1_t1.add(LocalTime.of(8, 20)); // 08:20
            horarios_inicio_d1_t1.add(LocalTime.of(14, 0)); // 14:00

            List<LocalTime> horarios_inicio_d1_t2 = new ArrayList<>();
            horarios_inicio_d1_t2.add(LocalTime.of(9, 20)); // 09:20
            horarios_inicio_d1_t2.add(LocalTime.of(15, 0)); // 15:00

            List<LocalTime> horarios_inicio_d1_t3 = new ArrayList<>();
            horarios_inicio_d1_t3.add(LocalTime.of(10, 20)); // 10:20
            horarios_inicio_d1_t3.add(LocalTime.of(16, 0)); // 16:00

            List<LocalTime> horarios_inicio_d2_t1 = new ArrayList<>();
            horarios_inicio_d2_t1.add(LocalTime.of(11, 20)); // 11:20

            List<LocalTime> horarios_inicio_d2_t2 = new ArrayList<>();
            horarios_inicio_d2_t2.add(LocalTime.of(12, 20)); // 12:20

            List<LocalTime> horarios_inicio_d3_t1 = new ArrayList<>();
            horarios_inicio_d3_t1.add(LocalTime.of(13, 20)); // 13:20
            horarios_inicio_d3_t1.add(LocalTime.of(14, 20)); // 14:20
            horarios_inicio_d3_t1.add(LocalTime.of(15, 20)); // 15:20

            // Lista de horários de fim
            List<LocalTime> horarios_fim_d1_t1 = new ArrayList<>();
            horarios_fim_d1_t1.add(LocalTime.of(9, 20)); // 09:20
            horarios_fim_d1_t1.add(LocalTime.of(15, 0)); // 15:00

            List<LocalTime> horarios_fim_d1_t2 = new ArrayList<>();
            horarios_fim_d1_t2.add(LocalTime.of(10, 20)); // 10:20
            horarios_fim_d1_t2.add(LocalTime.of(16, 0)); // 16:00

            List<LocalTime> horarios_fim_d1_t3 = new ArrayList<>();
            horarios_fim_d1_t3.add(LocalTime.of(11, 20)); // 11:20
            horarios_fim_d1_t3.add(LocalTime.of(17, 0)); // 17:00

            List<LocalTime> horarios_fim_d2_t1 = new ArrayList<>();
            horarios_fim_d2_t1.add(LocalTime.of(12, 20)); // 12:20

            List<LocalTime> horarios_fim_d2_t2 = new ArrayList<>();
            horarios_fim_d2_t2.add(LocalTime.of(13, 20)); // 13:20

            List<LocalTime> horarios_fim_d3_t1 = new ArrayList<>();
            horarios_fim_d3_t1.add(LocalTime.of(14, 20)); // 14:20
            horarios_fim_d3_t1.add(LocalTime.of(15, 20)); // 15:20
            horarios_fim_d3_t1.add(LocalTime.of(16, 20)); // 16:20

            // Criar disciplinas e associar turmas
            Disciplina d1 = new Disciplina("PCS1234", "Laboratório de Gatinhos");
            Disciplina d2 = new Disciplina("PTC5678", "Sistemas de DP");
            Disciplina d3 = new Disciplina("PEA4567", "Margaridas II");

            // Criando Turmas
            d1.criarTurma("2024_1_PCS1234", 
                          dias_d1_t1, 
                          horarios_inicio_d1_t1,
                          horarios_fim_d1_t1,
                          "Semanal",
                          100,
                          "Antônio Fisher",
                          LocalDate.of(2024, 2, 1),
                          LocalDate.of(2024, 10, 1),
                          true,
                          100,
                          "Windows"
                          );
            
            d1.criarTurma("2024_2_PCS1234", 
                          dias_d1_t2, 
                          horarios_inicio_d1_t2,
                          horarios_fim_d1_t2,
                          "Semanal",
                          50,
                          "Antônio Fisher",
                          LocalDate.of(2024, 3, 1),
                          LocalDate.of(2024, 6, 30),
                          true,
                          50,
                          "Linux"
                          );

            d1.criarTurma("2024_3_PCS1234", 
                          dias_d1_t3, 
                          horarios_inicio_d1_t3,
                          horarios_fim_d1_t3,
                          "Quinzenal",
                          100,
                          "Antônio Fisher",
                          LocalDate.of(2024, 3, 30),
                          LocalDate.of(2024, 10, 1),
                          true,
                          100,
                          "Windows"
                          );
            
            d2.criarTurma("2024_1_PTC5678", 
                          dias_d2_t1, 
                          horarios_inicio_d2_t1,
                          horarios_fim_d2_t1,
                          "Quinzenal",
                          100,
                          "Antônio Fisher",
                          LocalDate.of(2024, 8, 4),
                          LocalDate.of(2024, 12, 13),
                          true,
                          100,
                          "Windows"
                          );

            d2.criarTurma("2024_2_PTC5678", 
                          dias_d2_t2, 
                          horarios_inicio_d2_t2,
                          horarios_fim_d2_t2,
                          "Quinzenal",
                          100,
                          "Antonio Levy",
                          LocalDate.of(2024, 5, 4),
                          LocalDate.of(2024, 12, 13),
                          true,
                          null,
                          null
                          );

            // Turma sem computador
            d1.criarTurma("2024_1_PEA5669", 
                          dias_d3_t1, 
                          horarios_inicio_d3_t1,
                          horarios_fim_d3_t1,
                          "Semanal",
                          100,
                          "Daniel Ribeiro",
                          LocalDate.of(2024, 2, 1),
                          LocalDate.of(2024, 10, 1),
                          false,
                          null,
                          null
                          );
            // Criar Atividades
            // Atividade com Computador
            Atividade a1 = new Atividade("Workshop de Python da Skyrats", 
                             15,
                             LocalDate.of(2024, 2, 1),
                             false, 
                             "Quarta-feira",
                            LocalTime.of(14, 0),
                            LocalTime.of(16, 0),
                            15, 
                            "Windows"
                             );

            // Atividade sem Computador
            Atividade a2 = new Atividade("Palestra sobre Empoderamento Feminino", 
                             30,
                             LocalDate.of(2024, 2, 1),
                             true, 
                             "Sexta-feira",
                            LocalTime.of(13, 0),
                            LocalTime.of(14, 0)
                             );

            // Criar salas
            // Salas sem computadores
            salas.criarSala("D1-01", 200, true, 0, null, null);
            salas.criarSala("D1-02", 50, true, 0, null, null);
            salas.criarSala("D1-03", 100, false, 0, null, null);

            // Salas com computadores
            salas.criarSala("D1-04", 200, false, 20, "Windows", "Michelet");
            salas.criarSala("GD-01", 50, true, 30, "Linux", "Daniel");
            salas.criarSala("GD-01", 50, false, 10, "Windows", "Fátima");


            
            



        };
    }
}
