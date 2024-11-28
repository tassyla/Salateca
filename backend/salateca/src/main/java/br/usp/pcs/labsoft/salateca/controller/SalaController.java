package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final GerenciadorDeSala gerenciadorDeSalas;
    public SalaController(GerenciadorDeSala gerenciadorDeSalas) {
        this.gerenciadorDeSalas = gerenciadorDeSalas;
    }

    // @GetMapping("/listar") // Listar todas as salas
    // public ResponseEntity<List<Map<String, Object>>> listarSalas() { 

    //     List<Sala> salas = gerenciadorDeSalas.listarSalas();
    //     List<Map<String, Object>> salasMap = salas.stream().map(sala -> Map.of(
    //         "codigo", sala.getCodigo(),
    //         "capacidade", sala.getCapacidade(),
    //         "acessibilidade", sala.getAcessibilidade(),
    //         "quantidadeComputadores", sala.getQuantidadeComputadores(),
    //         "sistemaOperacional", sala.getSistemaOperacional(),
    //         "tecnicosResponsaveis", sala.getTecnicoResponsavel()
    //     )).collect(Collectors.toList());

    //    return ResponseEntity.ok(salasMap);
    // }

    @GetMapping("/buscar/{codigo}") // Buscar uma sala específica
    public ResponseEntity<Map<String, Object>> buscarSala(@PathVariable String codigo) {
        Sala sala = gerenciadorDeSalas.buscarSala(codigo);

        if (sala == null) {
             System.out.println("Sala não encontrada para o código: " + codigo); // Mensagem no terminal
            return ResponseEntity.ok().build();
        }

            // Criar o mapa da sala com as informações necessárias
            Map<String, Object> salaMap = Map.of(
                    "codigo", sala.getCodigo(),
                    "capacidade", sala.getCapacidade(),
                    "acessibilidade", sala.getAcessibilidade(),
                    "quantidadeComputadores", sala.getQuantidadeComputadores(),
                    "sistemaOperacional", sala.getSistemaOperacional(),
                    "tecnicosResponsaveis", sala.getTecnicoResponsavel()
            );

            return ResponseEntity.ok(salaMap);
    }


    @PostMapping("/criar") // Criar uma nova sala
    public Sala criarSala(@RequestBody String codigo,
                          @RequestBody int capacidade,
                          @RequestBody Boolean acessibilidade,
                          @RequestBody int quantidadeComputadores,
                          @RequestBody String sistemaOperacional,
                          @RequestBody String tecnicosResponsaveis) { 
        return gerenciadorDeSalas.criarSala(codigo, capacidade, acessibilidade, 
                                            quantidadeComputadores, sistemaOperacional, 
                                            tecnicosResponsaveis);
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
