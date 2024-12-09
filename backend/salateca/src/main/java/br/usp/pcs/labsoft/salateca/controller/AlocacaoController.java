package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.usp.pcs.labsoft.salateca.service.AtividadeService;
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
    private final AtividadeService atividadeService;

    public AlocacaoController(GerenciadorDeSala gerenciadorDeSalas, DisciplinaService disciplinaService, AtividadeService atividadeService) {
        this.gerenciadorDeSalas = gerenciadorDeSalas;
        this.disciplinaService = disciplinaService;
        this.atividadeService = atividadeService;
    }

    @GetMapping("/exibir-salas-turma/{codigo_turma}")
    public ResponseEntity<List<Map<String, Object>>> exibirSalasParaTurma(@PathVariable("codigo_turma") String codigoTurma) {
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

    @GetMapping("/exibir-salas-atividade/{codigo_atividade}")
    public ResponseEntity<List<Map<String, Object>>> exibirSalasParaAtividade(@PathVariable("codigo_atividade") String codigoAtividade) {

        Optional<Atividade> atividadeOpt = atividadeService.buscarAtividade(codigoAtividade);

        if (atividadeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(List.of(Map.of("erro", "Atividade não encontrada para o codigo fornecido")));
        }

        Horario horario = atividadeOpt.get().getHorario();
        Map<String, Object> horarioMap = Map.of(
            "diaDaSemana", horario.getDiaDaSemana(),
            "horarioInicio", horario.getHorarioInicio().toString(),
            "horarioFim", horario.getHorarioFim().toString(),
            "recorrencia", horario.getRecorrencia()
        );
            
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

        return (ResponseEntity<List<Map<String, Object>>>) Map.of(
            "horario", horarioMap,
            "salasDisponiveis", salasDisponiveis
        );
    
}


@GetMapping("/listar")
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

// Alocar sala para atividade
// Recebe codigo da atividade e da sala
// Cria uma alocação

@GetMapping("/alocar-sala-atividade/{codigo_atividade}/{codigo_sala}")
public ResponseEntity<Map<String, Object>> alocarSalaParaAtividade(@PathVariable("codigo_atividade") String codigoAtividade, @PathVariable("codigo_sala") String codigoSala) {
    // Busca a atividade pelo código fornecido
    Optional<Atividade> atividadeOpt = atividadeService.buscarAtividade(codigoAtividade);

    // Verifica se a atividade foi encontrada
    if (atividadeOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Atividade não encontrada para o código fornecido"));
    }

    // Busca a sala pelo código fornecido
    Sala sala = gerenciadorDeSalas.buscarSala(codigoSala);

    // Verifica se a sala foi encontrada
    if (sala == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Sala não encontrada para o código fornecido"));
    }

    Alocacao alocacao = sala.alocarSala(atividadeOpt.get().getHorario());
    gerenciadorDeSalas.save(sala);

    // Retorna a alocação criada
    return ResponseEntity.ok(Map.of(
        "dataInicio", alocacao.getHorario().getDataInicio(),
        "dataFim", alocacao.getHorario().getDataFim(),
        "diaDaSemana", alocacao.getHorario().getDiaDaSemana(),
        "horarioInicio", alocacao.getHorario().getHorarioInicio(),
        "horarioFim", alocacao.getHorario().getHorarioFim(),
        "recorrencia", alocacao.getHorario().getRecorrencia(),
        "nomeAtividade", atividadeOpt.get().getNome(),
        "codigoSala", sala.getCodigo()
    ));
    }

// Alocar sala para turma
// Recebe codigo da turma e da sala
// Cria uma alocação

@GetMapping("/alocar-sala-turma/{codigo_turma}/{codigo_sala}/{id_horario}")
public ResponseEntity<Map<String, Object>> alocarSalaParaTurma(@PathVariable("codigo_turma") String codigoTurma, @PathVariable("codigo_sala") String codigoSala, @PathVariable("id_horario") int idHorario) {
    // Busca a turma pelo código fornecido
    Turma turma = null;
    for (Disciplina disciplina : disciplinaService.listarDisciplinas()) {
        turma = disciplina.getTurmaByCodigo(codigoTurma);
        if (turma != null) {
            break;
        }
    }

    // Verifica se a turma foi encontrada
    if (turma == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Turma não encontrada para o código fornecido"));
    }

    // Busca a sala pelo código fornecido
    Sala sala = gerenciadorDeSalas.buscarSala(codigoSala);

    // Verifica se a sala foi encontrada
    if (sala == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Sala não encontrada para o código fornecido"));
    }

    // Busca o horário da turma pelo id fornecido por um loop entre os horários da turma
    Horario horario = null;
    for (Horario h : turma.getHorarios()) {
        if (h.getId() == idHorario) {
            horario = h;
            break;
        }
    }

    Alocacao alocacao = sala.alocarSala(horario);
    gerenciadorDeSalas.save(sala);

    // Retorna a alocação criada
    return ResponseEntity.ok(Map.of(
        "dataInicio", alocacao.getHorario().getDataInicio(),
        "dataFim", alocacao.getHorario().getDataFim(),
        "diaDaSemana", alocacao.getHorario().getDiaDaSemana(),
        "horarioInicio", alocacao.getHorario().getHorarioInicio(),
        "horarioFim", alocacao.getHorario().getHorarioFim(),
        "recorrencia", alocacao.getHorario().getRecorrencia(),
        "codigoTurma", turma.getCodigo(),
        "codigoDisciplina", turma.getDisciplina().getCodigo(),
        "nomeDisciplina", turma.getDisciplina().getNome(),
        "codigoSala", sala.getCodigo()
    ));
}
}
