package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String codigo;
    @OneToMany(mappedBy = "disciplina")
    private List<Turma> turmas;

    public Disciplina() {
    }

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.turmas = new ArrayList<>(); 
    }

    public Collection<Turma> getTurmas() {
        return this.turmas;
    }

}
