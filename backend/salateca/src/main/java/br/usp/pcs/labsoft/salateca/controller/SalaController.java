package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;
import br.usp.pcs.labsoft.salateca.entity.Sala;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final GerenciadorDeSala gerenciadorDeSalas;
    public SalaController(GerenciadorDeSala gerenciadorDeSalas) {
        this.gerenciadorDeSalas = gerenciadorDeSalas;
    }

    @GetMapping("") // Listar todas as salas
    public List<Sala> listarSalas() { 
        return gerenciadorDeSalas.listarSalas();
    }

    @GetMapping("/{codigo}") // Buscar uma sala específica
    public Sala buscarSala(@PathVariable String codigo) { 
        return gerenciadorDeSalas.buscarSala(codigo);
     }

    @PostMapping("/criar") // Criar uma nova sala
    public Sala criarSala(@RequestBody Sala sala) { 
        return gerenciadorDeSalas.criarSala(sala);
     }

    @PutMapping("/{codigo}") // Atualizar uma sala existente
    public Sala atualizarSala(@PathVariable String codigo, @RequestBody Sala sala) { 
        return gerenciadorDeSalas.atualizarSala(codigo, sala);
    }

    @DeleteMapping("/{codigo}") // Excluir uma sala
    public void excluirSala(@PathVariable String codigo) { 
        gerenciadorDeSalas.excluirSala(codigo);
     }


}
