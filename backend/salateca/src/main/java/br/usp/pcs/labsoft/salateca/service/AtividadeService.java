package br.usp.pcs.labsoft.salateca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import br.usp.pcs.labsoft.salateca.entity.Atividade;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeAtividades;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AtividadeService {

    @Autowired
    private GerenciadorDeAtividades gerenciadorDeAtividades;

    public void criarAtividades() {
        // Criação manual de atividade
        // Não serão criadas atividade fora dessa função
        Atividade atividade1 = new Atividade("111","Workshop de Python da Skyrats", 15, LocalDate.of(2024, 2, 1), false, "Quarta-feira", LocalTime.of(14, 0), LocalTime.of(16, 0), 15, "Windows");
        Atividade atividade2 = new Atividade("222", "Workshop de Front", 30, LocalDate.of(2024, 3, 1), false, "Quinta-feira", LocalTime.of(15, 0), LocalTime.of(16, 0), 15, "Windows");
        Atividade atividade3 = new Atividade("333", "Workshop de Back", 45, LocalDate.of(2024, 4, 1), false, "Sexta-feira", LocalTime.of(16, 0), LocalTime.of(16, 0), 15, "Windows");

        // Salvando as atividade no repositório
        // Para depois ter acesso à lista de todas as atividade
        gerenciadorDeAtividades.save(atividade1);
        gerenciadorDeAtividades.save(atividade2);
        gerenciadorDeAtividades.save(atividade3);
    }

    // Retorna a lista de todas as atividade existentes
    public List<Atividade> listarAtividades() {
        return gerenciadorDeAtividades.findAll();
    }

    // Retorna a atividade com o código especificado
    public Optional<Atividade> buscarAtividade(String codigo) {
        return gerenciadorDeAtividades.findByCodigo(codigo);
    }
    
}
