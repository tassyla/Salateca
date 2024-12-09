package br.usp.pcs.labsoft.salateca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import br.usp.pcs.labsoft.salateca.dto.CriarSalaDTO;
import br.usp.pcs.labsoft.salateca.dto.EditarSalaDTO;
import br.usp.pcs.labsoft.salateca.entity.ComputadorSala;
import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;

import java.util.List;

import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final GerenciadorDeSala gerenciadorDeSalas;
    public SalaController(GerenciadorDeSala gerenciadorDeSalas) {
        this.gerenciadorDeSalas = gerenciadorDeSalas;
    }

@GetMapping("/listar") // Listar todas as salas
public ResponseEntity<List<Map<String, Object>>> listarTodasSalas() {
    List<Sala> salas = gerenciadorDeSalas.listarSalas();

    if (salas == null || salas.isEmpty()) {
        System.out.println("Nenhuma sala encontrada."); // Mensagem no terminal
        return ResponseEntity.noContent().build();
    }


// Transformar a lista de objetos Sala em uma lista de mapas com as informações necessárias
List<Map<String, Object>> salasMap = salas.stream()
        .map(sala -> {
            Map<String, Object> map = new HashMap<>();
            map.put("codigo", sala.getCodigo());
            map.put("capacidade", sala.getCapacidade());
            map.put("acessibilidade", sala.getAcessibilidade());
            map.put("quantidadeComputadores", sala.getQuantidadeComputadores());
            map.put("sistemaOperacional", sala.getSistemaOperacional());
            map.put("tecnicoResponsavel", sala.getTecnicoResponsavel());
            return map;
        })
        .collect(Collectors.toList());

    return ResponseEntity.ok(salasMap);
}


    @GetMapping("/buscar/{codigo}") // Buscar uma sala específica
    public ResponseEntity<Map<String, Object>> buscarSala(@PathVariable String codigo) {
        Sala sala = gerenciadorDeSalas.buscarSala(codigo);

        if (sala == null) {
             System.out.println("Sala não encontrada para o código: " + codigo); // Mensagem no terminal
            return ResponseEntity.ok().build();
        }

            Map<String, Object> salaMap;
            // Criar o mapa da sala com as informações necessárias
            if (sala.getComputadorSala() == null) {
                salaMap = Map.of(
                        "codigo", sala.getCodigo(),
                        "capacidade", sala.getCapacidade(),
                        "acessibilidade", sala.getAcessibilidade()
                );
            }
            else {
            salaMap = Map.of(
                    "codigo", sala.getCodigo(),
                    "capacidade", sala.getCapacidade(),
                    "acessibilidade", sala.getAcessibilidade(),
                    "quantidadeComputadores", sala.getQuantidadeComputadores(),
                    "sistemaOperacional", sala.getSistemaOperacional(),
                    "tecnicoResponsavel", sala.getTecnicoResponsavel()
            );
            }

            return ResponseEntity.ok(salaMap);
    }


@PostMapping("/criar") // Criar uma nova sala
public ResponseEntity<?> criarSala(@RequestBody CriarSalaDTO criarSalaDTO) {
    try {
        // Criar a nova sala usando o método do gerenciador
        Sala novaSala = gerenciadorDeSalas.criarSala(
                criarSalaDTO.getCodigo(),
                criarSalaDTO.getCapacidade(),
                criarSalaDTO.getAcessibilidade(),
                criarSalaDTO.getQuantidadeComputadores(),
                criarSalaDTO.getSistemaOperacional(),
                criarSalaDTO.gettecnicoResponsavel()
        );

        gerenciadorDeSalas.save(novaSala);

        // Retornar a resposta com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensagem", "Sala criada com sucesso!",
                "sala", Map.of(
                        "codigo", novaSala.getCodigo(),
                        "capacidade", novaSala.getCapacidade(),
                        "acessibilidade", novaSala.getAcessibilidade(),
                        "quantidadeComputadores", novaSala.getQuantidadeComputadores(),
                        "sistemaOperacional", novaSala.getSistemaOperacional(),
                        "tecnicoResponsavel", novaSala.getTecnicoResponsavel()
                )
        ));
    } catch (IllegalArgumentException e) {
        // Retornar erro caso os dados sejam inválidos
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "erro", "Erro ao criar a sala: " + e.getMessage()
        ));
    }
}


@PutMapping("/editar/{codigo}")
public ResponseEntity<?> editarSala(
        @PathVariable String codigo,
        @RequestBody EditarSalaDTO editarSalaDTO) {
    Sala salaOpt = gerenciadorDeSalas.buscarSala(codigo);

    if (salaOpt != null) {
        Sala sala = salaOpt;
        ComputadorSala computadorSala = sala.getComputadorSala();

        // Atualizar as propriedades da sala
        sala.setCapacidade(editarSalaDTO.getCapacidade());
        sala.setAcessibilidade(editarSalaDTO.getAcessibilidade());

        if (computadorSala == null) {
            computadorSala = new ComputadorSala();
            sala.setComputadorSala(computadorSala);
        }
        computadorSala.setQuantidadeComputadores(editarSalaDTO.getQuantidadeComputadores());
        computadorSala.setSistemaOperacional(editarSalaDTO.getSistemaOperacional());
        computadorSala.setTecnicoResponsavel(editarSalaDTO.getTecnicoResponsavel());

        // Salvar a sala atualizada e retornar uma resposta apropriada
        gerenciadorDeSalas.save(sala);
        if (computadorSala.getSistemaOperacional() == null) {
            return ResponseEntity.ok(Map.of(
                    "mensagem", "Sala editada com sucesso!",
                    "sala", Map.of(
                            "codigo", sala.getCodigo(),
                            "capacidade", sala.getCapacidade(),
                            "acessibilidade", sala.getAcessibilidade()
                    )
            ));
        }

        return ResponseEntity.ok(Map.of(
                "mensagem", "Sala editada com sucesso!",
                "sala", Map.of(
                        "codigo", sala.getCodigo(),
                        "capacidade", sala.getCapacidade(),
                        "acessibilidade", sala.getAcessibilidade(),
                        "quantidadeComputadores", sala.getQuantidadeComputadores(),
                        "sistemaOperacional", sala.getSistemaOperacional(),
                        "tecnicoResponsavel", sala.getTecnicoResponsavel()
                )
        ));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", "Sala não encontrada para o código fornecido"));
    }
}

    @DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<?> excluirSala(@PathVariable String codigo) {
        Sala sala = gerenciadorDeSalas.buscarSala(codigo);
        if (sala == null) {
             System.out.println("Sala não encontrada para o código: " + codigo); // Mensagem no terminal
            return ResponseEntity.ok().build();
        }
        gerenciadorDeSalas.excluirSala(codigo);

        return ResponseEntity.ok(Map.of("mensagem", "Sala excluída com sucesso."));

    }
}
