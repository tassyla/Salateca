package br.usp.pcs.labsoft.salateca;

import br.usp.pcs.labsoft.salateca.entity.Disciplina;
import br.usp.pcs.labsoft.salateca.repository.GerenciadorDeDisciplinas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalatecaApplicationTests {

    @Autowired
    private GerenciadorDeDisciplinas gerenciadorDeDisciplinas;

    @BeforeEach
    void setUp() {
        // Garantir que o banco de dados comece limpo antes de cada teste
        gerenciadorDeDisciplinas.deleteAll();
    }

    @Test
    void testarOperacoesComDisciplinas() {
        // 1. Criar 3 disciplinas
        Disciplina disciplina1 = new Disciplina("Cálculo 1", "MAT001");
        Disciplina disciplina2 = new Disciplina("Algoritmos e Estruturas de Dados", "INF002");
        Disciplina disciplina3 = new Disciplina("Sistemas Operacionais", "INF003");

        gerenciadorDeDisciplinas.save(disciplina1);
        gerenciadorDeDisciplinas.save(disciplina2);
        gerenciadorDeDisciplinas.save(disciplina3);

        // // 2. Listar todas as disciplinas
        // List<Disciplina> disciplinas = gerenciadorDeDisciplinas.findAll();
        // System.out.println("Disciplinas após criação:");
        // disciplinas.forEach(disciplina -> System.out.println(disciplina));

        // // 3. Retornar uma disciplina pelo código
        // Optional<Disciplina> disciplinaBuscada = gerenciadorDeDisciplinas.findByCodigo("INF002");
        // if (disciplinaBuscada.isPresent()) {
        //     System.out.println("Disciplina encontrada pelo código INF002: " + disciplinaBuscada.get());
        // } else {
        //     System.out.println("Disciplina com código INF002 não encontrada.");
        // }

        // // 4. Deletar todas as disciplinas
        // gerenciadorDeDisciplinas.deleteAll();

        // // 5. Listar todas as disciplinas novamente
        // List<Disciplina> disciplinasDeletadas = gerenciadorDeDisciplinas.findAll();
        // System.out.println("Disciplinas após deleção:");
        // if (disciplinasDeletadas.isEmpty()) {
        //     System.out.println("Nenhuma disciplina encontrada após a deleção.");
        // } else {
        //     disciplinasDeletadas.forEach(disciplina -> System.out.println(disciplina));
        // }
    }
}
