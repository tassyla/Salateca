package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.usp.pcs.labsoft.salateca.service.DisciplinaService;
import br.usp.pcs.labsoft.salateca.entity.Turma;
import java.util.List;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final DisciplinaService gerenciadorDeTurmas;
    private final DisciplinaService gerenciadorDeDisciplinas;

    public TurmaController(DisciplinaService gerenciadorDeTurmas) {
        this.gerenciadorDeTurmas = gerenciadorDeTurmas;
        this.gerenciadorDeDisciplinas = gerenciadorDeDisciplinas;
    }

    @GetMapping("") // Listar todas as turmas
    public List<Turma> listarTurmas() { 
        return gerenciadorDeDisciplinas.listarTurmas();
    }

    @GetMapping("/{codigo}") // Buscar uma turma específica
    public Turma buscarTurma(@PathVariable String codigo) { 
        return gerenciadorDeDisciplinas.buscarTurma(codigo);
    }

    @PostMapping("/criar") // Criar uma nova turma
    public Turma criarTurma(@RequestBody Turma turma) { 
        return gerenciadorDeDisciplinas.criarTurma(turma);
    }

    @PutMapping("/{codigo}") // Atualizar uma turma existente
    public Turma atualizarTurma(@PathVariable String codigo, 
                                @RequestBody Turma turma,
                                ) { 
        return gerenciadorDeDisciplinas.atualizarTurma(codigo, turma);
    }

    @DeleteMapping("/{codigo}") // Excluir uma turma
    public void excluirTurma(@PathVariable String codigo) { 
        gerenciadorDeDisciplinas.excluirTurma(codigo);
    }
}
