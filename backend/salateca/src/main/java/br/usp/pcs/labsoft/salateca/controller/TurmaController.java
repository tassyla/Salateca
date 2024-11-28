package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
            Collection<Turma> turmas = disciplinaOpt.get().listarTurmas();
            return ResponseEntity.ok(turmas); // Retorna as turmas com status 200
        }
    
        // Retorna um JSON com o erro e status 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido"));
    }
    

    @GetMapping("/buscar/{codigoDisciplina}/{codigo}") // Buscar uma turma específica
    public Map<String, Object> buscarTurma(@PathVariable String codigoDisciplina,
                             @PathVariable String codigo) { 
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigo);
        
        if (disciplinaOpt.isPresent()) {
            Turma turma = disciplinaOpt.get().getTurmaByCodigo(codigo);
            
            Map<String, Object> turmaMap = Map.of(
                    "codigo", turma.getCodigo(),
                    "nomeDiscipina", turma.getDisciplina().getNome(),
                    "quantidadedeAlunos", turma.getQuantidadeAlunos(),
                    "professor", turma.getProfessor(),
                    "acesibilidade", turma.getAcessibilidade()
                    
                    

            );

            return turmaMap;
        }

        ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido")));

        return null;
    }

    @PostMapping("/criar") // Criar uma nova turma
    public Turma criarTurma(@RequestBody String codigoDisciplina, 
                            @RequestBody String codigo,
                            @RequestBody List<String> diasDaSemana,
                            @RequestBody List<LocalTime> horariosInicios, 
                            @RequestBody List<LocalTime> horariosFins, 
                            @RequestBody String recorrencia, 
                            @RequestBody int quantidadeAlunos, 
                            @RequestBody String professor, 
                            @RequestBody LocalDate dataInicio, 
                            @RequestBody LocalDate dataFim, 
                            @RequestBody Boolean acessibilidade,
                            @RequestBody Integer quantidadeComputadores, 
                            @RequestBody String sistemaOperacional) { 
        Optional<Disciplina> disciplinaOpt = this.gerenciadorDeDisciplinas.findByCodigo(codigo);
        
        if (disciplinaOpt.isPresent()) {
            return disciplinaOpt.get().criarTurma(codigo, diasDaSemana, horariosInicios, horariosFins, 
                                                  recorrencia, quantidadeAlunos, professor, dataInicio, 
                                                  dataFim, acessibilidade, quantidadeComputadores, 
                                                  sistemaOperacional);
        }

        ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(Map.of("erro", 
                "Não foi encontrada uma disciplina com o código fornecido")));

        return null;
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
