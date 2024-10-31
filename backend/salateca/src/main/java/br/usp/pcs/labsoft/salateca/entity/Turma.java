package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Turma {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String codigo;
    @ManyToOne
    private Disciplina disciplina;

    public Turma(){
        
    }

    public Turma(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getCodigo () {
        return this.codigo;
    }

    public String getNome () {
        return this.nome;
    }
}
