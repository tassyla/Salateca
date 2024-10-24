package br.usp.pcs.labsoft.salateca.entity;

public class Turma {
    private String nome;
    private String id;

    public Turma(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getId () {
        return this.id;
    }

    public String getNome () {
        return this.nome;
    }
}
