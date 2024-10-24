package br.usp.pcs.labsoft.salateca.entity;

import java.util.ArrayList;
import java.util.Collection;

public class Disciplina {
    private String nome;
    private String id;
    private Collection<Turma> turmas;

    public Disciplina(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.turmas = new ArrayList<>(); 
    }

    public Collection<Turma> getTurmas() {
        return this.turmas;
    }

}
