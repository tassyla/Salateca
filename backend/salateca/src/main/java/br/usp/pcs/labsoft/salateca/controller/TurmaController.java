package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.usp.pcs.labsoft.salateca.entity.Turma;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import java.util.Optional;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.time.LocalTime;
import java.time.LocalDate;


@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final GerenciadorDeDisciplinas gerenciadorDeDisciplinas;

    public TurmaController(GerenciadorDeDisciplinas gerenciadorDeDisciplinas) {
        this.gerenciadorDeDisciplinas = gerenciadorDeDisciplinas;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> verificarRotas() {
        return ResponseEntity.ok(Map.of("mensagem", "Tudo certo com as rotas de turmas"));
    }

    @GetMapping("/listar/{codigoDisciplina}") // Listar todas as turmas
    public ResponseEntity<?> listarTurmas(@PathVariable String codigoDisciplina) {
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigoDisciplina);

        if (disciplinaOpt.isPresent()) {
            // Obter as turmas da disciplina
            Collection<Turma> turmas = disciplinaOpt.get().listarTurmas();

            // Converter as turmas para um formato JSON amigável
            List<Map<String, Object>> turmasMap = turmas.stream().map(turma -> {
                // Transformar lista de horários em uma lista de mapas
                List<Map<String, String>> horariosMap = turma.getHorarios().stream().map(horario -> Map.of(
                    "diaDaSemana", horario.getDiaDaSemana(),
                    "horarioInicio", horario.getHorarioInicio().toString(),
                    "horarioFim", horario.getHorarioFim().toString(),
                    "recorrencia", horario.getRecorrencia()
                )).toList();

                // Criar o mapa da turma incluindo os horários
                return Map.of(
                    "codigo", turma.getCodigo(),
                    "nomeDisciplina", turma.getDisciplina().getNome(),
                    "quantidadeDeAlunos", turma.getQuantidadeAlunos(),
                    "professor", turma.getProfessor(),
                    "acessibilidade", turma.getAcessibilidade(),
                    "horarios", horariosMap
                );
            }).toList();

            return ResponseEntity.ok(turmasMap); // Retorna a lista de turmas formatadas com status 200
        }

        // Retorna um JSON com o erro e status 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido"));
    }

    @GetMapping("/buscar/{codigoDisciplina}/{codigo}") // Buscar uma turma específica
    public ResponseEntity<Map<String, Object>> buscarTurma(@PathVariable String codigoDisciplina,
                                                        @PathVariable String codigo) {
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigoDisciplina);

        if (disciplinaOpt.isPresent()) {
            Turma turma = disciplinaOpt.get().getTurmaByCodigo(codigo);

            if (turma != null) {
                // Transformar lista de horários em uma lista de mapas
                List<Map<String, String>> horariosMap = turma.getHorarios().stream().map(horario -> Map.of(
                    "diaDaSemana", horario.getDiaDaSemana(),
                    "horarioInicio", horario.getHorarioInicio().toString(),
                    "horarioFim", horario.getHorarioFim().toString(),
                    "recorrencia", horario.getRecorrencia()
                )).toList();

                // Criar o mapa da turma incluindo os horários
                Map<String, Object> turmaMap = Map.of(
                        "codigo", turma.getCodigo(),
                        "nomeDisciplina", turma.getDisciplina().getNome(),
                        "quantidadeDeAlunos", turma.getQuantidadeAlunos(),
                        "professor", turma.getProfessor(),
                        "acessibilidade", turma.getAcessibilidade(),
                        "horarios", horariosMap // Adicionando a lista de horários
                );

                return ResponseEntity.ok(turmaMap);
            }
            // Caso a turma não seja encontrada
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Turma não encontrada"));
        }
        // Caso a disciplina não seja encontrada
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Disciplina não encontrada"));
    }


    @PostMapping("/criar") // Criar uma nova turma
    public ResponseEntity<?> criarTurma(@RequestParam String codigoDisciplina, 
                                        @RequestParam String codigo,
                                        @RequestParam List<String> diasDaSemana,
                                        @RequestParam List<LocalTime> horariosInicios, 
                                        @RequestParam List<LocalTime> horariosFins, 
                                        @RequestParam String recorrencia, 
                                        @RequestParam int quantidadeAlunos, 
                                        @RequestParam String professor, 
                                        @RequestParam LocalDate dataInicio, 
                                        @RequestParam LocalDate dataFim, 
                                        @RequestParam Boolean acessibilidade,
                                        @RequestParam Integer quantidadeComputadores, 
                                        @RequestParam String sistemaOperacional) {
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigoDisciplina);

        if (disciplinaOpt.isPresent()) {
            Turma novaTurma = disciplinaOpt.get().criarTurma(codigo, diasDaSemana, horariosInicios, horariosFins,
                    recorrencia, quantidadeAlunos, professor, dataInicio, dataFim, acessibilidade,
                    quantidadeComputadores, sistemaOperacional);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "mensagem", "Turma criada com sucesso!",
                    "turma", Map.of(
                            "codigo", novaTurma.getCodigo(),
                            "professor", novaTurma.getProfessor(),
                            "acessibilidade", novaTurma.getAcessibilidade()
                    )
            ));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido"));
    }

    @PutMapping("atualizar/{codigoDisciplina}/{codigo}") // Atualizar uma turma existente
    public Turma atualizarTurma(@PathVariable String codigoDisciplina, 
                                @PathVariable String codigo,
                                @RequestBody LocalDate dataInicio, 
                                @RequestBody LocalDate dataFim,
                                @RequestBody int quantidadeAlunos, 
                                @RequestBody Boolean acessibilidade,
                                @RequestBody String professor, 
                                @RequestBody String recorrencia,
                                @RequestBody List<String> diasDaSemana, 
                                @RequestBody List<LocalTime> horariosInicios,
                                @RequestBody List<LocalTime> horariosFins
                                ) { 
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigo);
        
        if (disciplinaOpt.isPresent()) {
            return disciplinaOpt.get().editarTurma(codigo, dataInicio, dataFim,
                                                  quantidadeAlunos, acessibilidade, professor, 
                                                  recorrencia, diasDaSemana, horariosInicios, horariosFins);
        }

        ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido")));

        return null;
    }

    @DeleteMapping("/excluir/{codigoDisciplina}/{codigo}") // Excluir uma turma
    public void excluirTurma(@PathVariable String codigoDisciplina,
                             @PathVariable String codigo) { 
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigoDisciplina);
        
        if (disciplinaOpt.isPresent()) {
            disciplinaOpt.get().deletarTurmaByCodigo(codigo);
        }

        ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido")));

    }
}
