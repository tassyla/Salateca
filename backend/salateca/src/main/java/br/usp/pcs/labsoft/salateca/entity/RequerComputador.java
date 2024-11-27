package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

@Entity
public class RequerComputador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantidadeComputadores;
    private String sistemaOperacional;

    // Um Turma/Atividade pode ou não requerer computadores
    @OneToOne
    private Turma turma;

    @OneToOne
    private Atividade atividade;

    public RequerComputador() {}

    // Construtor com parâmetros e obrigando a associação com a turma
    public RequerComputador(int quantidadeComputadores, String sistemaOperacional, Turma turma) {
        this.quantidadeComputadores = quantidadeComputadores;
        this.sistemaOperacional = sistemaOperacional;
        this.turma = turma;  // A turma é obrigatória
    }

    // ----------------- Getters e setters -----------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeComputadores() {
        return quantidadeComputadores;
    }

    public void setQuantidadeComputadores(int quantidadeComputadores) {
        this.quantidadeComputadores = quantidadeComputadores;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}
