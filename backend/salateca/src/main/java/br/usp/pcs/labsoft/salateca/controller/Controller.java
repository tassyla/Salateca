package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.usp.pcs.labsoft.salateca.repository.SalaRepository;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;
import br.usp.pcs.labsoft.salateca.entity.Turma;

@RestController
@RequestMapping(produces = "application/json")
// @CrossOrigin(origins={"http://localhost:3000"})
public class Controller {
    private final SalaRepository salaRepository;
    private final GerenciadorDeDisciplinas gerenciadorDeDisciplinas;

    public Controller(SalaRepository salaRepository, 
                      GerenciadorDeDisciplinas gerenciadorDeDisciplinas) {
        this.salaRepository = salaRepository;
        this.gerenciadorDeDisciplinas = gerenciadorDeDisciplinas;
    }
    
    // Turmas

    // @GetMapping(path="/api/turmas/{idDisciplina}")
    // public Iterable<Turma> getTurmas(@PathVariable int idDisciplina) {
    //     return gerenciadorDeDisciplinas.getTurmas(idDisciplina);
    // }

    // @GetMapping(path="/api/turmas/{idDisciplina}/{idTurma}")
    // public Turma getTurma(@PathVariable int idDisciplina, @PathVariable int idTurma) {
    //     return gerenciador.getTurma(idDisciplina, idTurma);
    // }
}