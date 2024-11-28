package br.usp.pcs.labsoft.salateca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;

@Service
public class DisciplinaService {

    @Autowired
    private GerenciadorDeDisciplinas gerenciadorDeDisciplinas;

    public void criarDisciplinas() {
        // Criação manual de disciplinas
        // Não serão criadas disciplinas fora dessa função
        Disciplina disciplina1 = new Disciplina("MAT001", "Cálculo 1");
        Disciplina disciplina2 = new Disciplina("INF002", "Algoritmos e Estruturas de Dados");
        Disciplina disciplina3 = new Disciplina("INF003", "Sistemas Operacionais");

        // Salvando as disciplinas no repositório
        // Para depois ter acesso à lista de todas as disciplinas
        gerenciadorDeDisciplinas.save(disciplina1);
        gerenciadorDeDisciplinas.save(disciplina2);
        gerenciadorDeDisciplinas.save(disciplina3);
    }

    // Retorna a lista de todas as disciplinas existentes
    public List<Disciplina> listarDisciplinas() {
        return gerenciadorDeDisciplinas.findAll();
    }
    

}
