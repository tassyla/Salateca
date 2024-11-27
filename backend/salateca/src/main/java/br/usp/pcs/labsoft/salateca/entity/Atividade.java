package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;
import java.util.Date;



@Entity
public class Atividade {

    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private int quantidadeParticipantes;
    private Date data;

    private boolean acessibilidade;

    // Uma atividade pode ou não requerer computadores
    // Se a atividade for excluída, a requisição de computadores
    // associada a ela também é excluída
    @OneToOne(mappedBy = "atividade", cascade = CascadeType.ALL)
    private RequerComputador requerComputador;


    public Atividade() {}

    public Atividade(String nome, int quantidadeParticipantes, Date data, boolean acessibilidade,
                     Integer quantidadeComputadores, String sistemaOperacional) {
        this.nome = nome;
        this.quantidadeParticipantes = quantidadeParticipantes;
        this.data = data;
        this.acessibilidade = acessibilidade;

        if (quantidadeComputadores != null || sistemaOperacional != null) {
            this.requerComputador = new RequerComputador(this, quantidadeComputadores, sistemaOperacional);
        }
    }

    // ----------------- Getters e setters -----------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeParticipantes() {
        return quantidadeParticipantes;
    }

    public void setQuantidadeParticipantes(int quantidadeParticipantes) {
        this.quantidadeParticipantes = quantidadeParticipantes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(boolean acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public RequerComputador getRequerComputador() {
        return requerComputador;
    }

    public void setRequerComputador(RequerComputador requerComputador) {
        this.requerComputador = requerComputador;
    }
}

