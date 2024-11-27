package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private String nome;

    // Cada disciplina pode ter várias turmas
    // Quando uma disciplina é excluída, as turmas
    // associadas a ela também são excluídas
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private Collection<Turma> turmas;

    // Construtores
    public Disciplina() {}

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // ----------------- Getters e setters -----------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Collection<Turma> turmas) {
        this.turmas = turmas;
    }

    // Criar turma e associá-la à disciplina
    public Turma criarTurma(String codigo, String nome, boolean requerComputador, List<String> diasDaSemana, 
                            List<LocalTime> horariosInicios, List<LocalTime> horariosFins, 
                            Integer quantidadeComputadores, String sistemaOperacional) {

        Turma novaTurma = new Turma(codigo, nome, this, quantidadeComputadores, sistemaOperacional,
                                    diasDaSemana, horariosInicios, horariosFins);

        
                                    
        this.turmas.add(novaTurma);

        return novaTurma;
    }


    // Método para listar todas as turmas
    public Collection<Turma> listarTurmas() {
        return turmas;
    }
}
