package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

@Entity
public class Turma {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private String nome;

    // Uma turma pode ou não requerer computadores
    // Se uma turma for excluída, a requisição de computadores
    // associada a ela também é excluída
    @OneToOne(mappedBy = "turma", cascade = CascadeType.ALL)
    private RequerComputador requerComputador;

    // Uma turma está associada a uma disciplina
    @ManyToOne
    private Disciplina disciplina;


    public Turma() {}


    public Turma(String codigo, String nome, Disciplina disciplina, Integer quantidadeComputadores, String sistemaOperacional) {
        this.codigo = codigo;
        this.nome = nome;
        this.disciplina = disciplina;

        // Cria um objeto RequerComputador se os parâmetros necessários forem passados
        if (quantidadeComputadores != null && sistemaOperacional != null) {
            this.requerComputador = new RequerComputador(this,quantidadeComputadores, sistemaOperacional);
        }
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

    public RequerComputador getRequerComputador() {
        return requerComputador;
    }

    public void setRequerComputador(RequerComputador requerComputador) {
        this.requerComputador = requerComputador;
    }
}
