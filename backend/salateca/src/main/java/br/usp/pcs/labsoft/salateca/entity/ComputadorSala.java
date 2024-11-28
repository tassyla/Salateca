package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ComputadorSala {
    @Id
    @GeneratedValue
    private int id;

    private int quantidadeComputadores;
    private String sistemaOperacional;
    private String tecnicoResponsavel;

    // Cada ComputadorSala está associado a uma sala
    @OneToOne(mappedBy = "sala")
    private Sala sala;

    public ComputadorSala() {
    }

    public ComputadorSala(int quantidadeComputadores, String sistemaOperacional, String tecnicoResponsavel, Sala sala) {
        this.quantidadeComputadores = quantidadeComputadores;
        this.sistemaOperacional = sistemaOperacional;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.sala = sala;
    }

    // ----------------- Getters e setters -----------------------
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

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(String tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
