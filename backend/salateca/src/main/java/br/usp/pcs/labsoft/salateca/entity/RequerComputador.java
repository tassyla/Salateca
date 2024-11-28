package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

@Entity
public class RequerComputador {

    @Id
    @GeneratedValue
    private int id;

    private int quantidadeComputadores;
    private String sistemaOperacional;

    // Um RequerComputador deve ser associado obrigatoriamente a uma Turma ou a uma Atividade
    @OneToOne
    private Turma turma;

    @OneToOne
    private Atividade atividade;

    public RequerComputador() {}


    // Construtores para associar a turma ou atividade


    public RequerComputador(Turma turma, Integer quantidadeComputadores, String sistemaOperacional) {
        this.turma = turma;
        this.atividade = null;
        this.quantidadeComputadores = quantidadeComputadores;
        this.sistemaOperacional = sistemaOperacional;
    }


    public RequerComputador(Atividade atividade, Integer quantidadeComputadores, String sistemaOperacional) {
        this.atividade = atividade;
        this.turma = null;
        this.quantidadeComputadores = quantidadeComputadores;
        this.sistemaOperacional = sistemaOperacional;
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
