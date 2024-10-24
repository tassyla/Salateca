package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.usp.pcs.labsoft.salateca.entity.GerenciadorDeAlocacoes;
import br.usp.pcs.labsoft.salateca.entity.Turma;

@RestController
@RequestMapping(produces = "application/json")
// @CrossOrigin(origins={"http://localhost:3000"})
public class Controller {
    private final GerenciadorDeAlocacoes gerenciador;

    public Controller(GerenciadorDeAlocacoes gerenciador) {
        this.gerenciador = gerenciador;
    }
 
    @GetMapping(path="/api/turmas/{idDisciplina}")
    public Iterable<Turma> getTurmas(@PathVariable int idDisciplina) {
        return gerenciador.getTurmas(idDisciplina);
    }
}