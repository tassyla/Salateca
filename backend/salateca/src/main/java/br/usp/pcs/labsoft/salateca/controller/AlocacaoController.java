package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.usp.pcs.labsoft.salateca.service.DisciplinaService;
import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.entity.Turma;
import br.usp.pcs.labsoft.salateca.entity.Horario;
import br.usp.pcs.labsoft.salateca.entity.Alocacao;
import br.usp.pcs.labsoft.salateca.entity.Atividade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoController {

    private final GerenciadorDeSala gerenciadorDeSalas;
    private final DisciplinaService disciplinaService;

    public AlocacaoController(GerenciadorDeSala gerenciadorDeSalas, DisciplinaService disciplinaService) {
        this.gerenciadorDeSalas = gerenciadorDeSalas;
        this.disciplinaService = disciplinaService;
    }
    @GetMapping("/alocarSalasTurma/{codigo_turma}")
    public ResponseEntity<List<Map<String, Object>>> alocarSalasParaTurma(@PathVariable("codigo_turma") String codigoTurma) {
        // Com o código da turma, é possível encontrar a disciplina e a turma correspondente
        Turma turma = null;
        for (Disciplina disciplina : disciplinaService.listarDisciplinas()) {
            turma = disciplina.getTurmaByCodigo(codigoTurma);
            if (turma != null) {
                break;
            }
        }
    
        // Verifica se existe mesmo uma turma com o código fornecido
        if (turma == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(List.of(Map.of("erro", "Turma não encontrada para o código fornecido")));
        }
    
        // Itera pelos horários da turma e retorna as salas disponíveis para cada horário
        List<Map<String, Object>> resultado = turma.getHorarios().stream().map(horario -> {
            // Estrutura de retorno das informações do Horário
            Map<String, Object> horarioMap = Map.of(
                    "diaDaSemana", horario.getDiaDaSemana(),
                    "horarioInicio", horario.getHorarioInicio().toString(),
                    "horarioFim", horario.getHorarioFim().toString(),
                    "recorrencia", horario.getRecorrencia()
            );
    
            // Obtém a lista de salas com conflitos
            List<Map.Entry<Sala, Boolean>> salasComConflitos = gerenciadorDeSalas.exibirSalasEConflitos(horario);
    
            List<Map<String, Object>> salasDisponiveis = salasComConflitos.stream().map(salaConflito -> {
                // Explicitamente retorna um Map<String, Object>
                Map<String, Object> salaMap = new HashMap<>();
                salaMap.put("codigo", salaConflito.getKey().getCodigo());  // Acessa o código da sala
                salaMap.put("capacidade", salaConflito.getKey().getCapacidade());  // Acessa a capacidade da sala
                salaMap.put("acessibilidade", salaConflito.getKey().getAcessibilidade());  // Acessa a acessibilidade
                salaMap.put("quantidadeDeComputadores", salaConflito.getKey().getQuantidadeComputadores());  // Acessa a quantidade de computadores
                salaMap.put("sistemaOperacional", salaConflito.getKey().getSistemaOperacional());  // Acessa o sistema operacional
                salaMap.put("conflito", salaConflito.getValue());  // Acessa o valor de conflito
                
                return salaMap;
            }).collect(Collectors.toList());
    
            // Retorna as informações do horário e as salas com conflito
            return Map.of(
                    "horario", horarioMap,
                    "salasDisponiveis", salasDisponiveis
            );
        }).collect(Collectors.toList());
    
        return ResponseEntity.ok(resultado);
    }

@GetMapping("/alocacoes")
public ResponseEntity<List<Map<String, Object>>> getAlocacoes(){
    // Buscar todas as salas cadastradas no gerenciador de salas
    List<Sala> salas = gerenciadorDeSalas.listarSalas();

    // Lista para armazenar as alocações resultantes
    List<Map<String, Object>> resultado = new ArrayList<>();

    // Itera sobre cada sala
    for (Sala sala : salas) {
        // Obtém as alocações da sala atual
        List<Alocacao> alocacoes = sala.getAlocacoes();

        // Itera sobre cada alocação na sala
        for (Alocacao alocacao : alocacoes) {
            // Mapa para armazenar as informações da alocação atual
            Map<String, Object> alocacaoMap = new HashMap<>();

            // Adiciona o código da sala
            alocacaoMap.put("codigoSala", sala.getCodigo());



            // Adiciona informações do horário da alocação
            Horario horario = alocacao.getHorario();
            alocacaoMap.put("recorrencia", horario.getRecorrencia());
            alocacaoMap.put("diaDaSemana", horario.getDiaDaSemana());
            alocacaoMap.put("horarioInicio", horario.getHorarioInicio());
            alocacaoMap.put("horarioFim", horario.getHorarioFim());

            // Verifica se a alocação é para uma disciplina ou para uma atividade
            if (alocacao.getHorario().getTurma() != null) {
                // Alocação para disciplina
                Turma turma = alocacao.getHorario().getTurma();
                Disciplina disciplina = turma.getDisciplina(); // Supondo que Turma tem um método getDisciplina()

                alocacaoMap.put("codigoTurma", turma.getCodigo());
                alocacaoMap.put("codigoDisciplina", disciplina.getCodigo());
                alocacaoMap.put("nomeDisciplina", disciplina.getNome());

                // Adiciona as datas de início e fim da alocação
                alocacaoMap.put("dataInicio", turma.getDataInicio());
                alocacaoMap.put("dataFim", turma.getDataFim());

            } else if (alocacao.getHorario().getAtividade() != null) {
                // Alocação para atividade
                Atividade atividade = alocacao.getHorario().getAtividade();
                alocacaoMap.put("nomeAtividade", atividade.getNome());

                // Adiciona as datas de início e fim da alocação
                alocacaoMap.put("dataInicio", atividade.getData());
                alocacaoMap.put("dataFim", atividade.getData());
            }

            // Adiciona o mapa da alocação atual à lista de resultados
            resultado.add(alocacaoMap);
        }
    }

    // Retorna a lista de alocações como resposta HTTP 200 OK
    return ResponseEntity.ok(resultado);
}

}
