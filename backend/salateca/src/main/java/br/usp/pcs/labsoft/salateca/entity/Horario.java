package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Horario {

    @Id
    @GeneratedValue
    private int id;

    private String diaDaSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String    recorrencia;

    // Cada horário pode ter uma ou nenhuma alocação
    @OneToOne(mappedBy = "horario")
    private Alocacao alocacao;

    // Cada horário está associado a uma turma ou atividade obrigatoriamente
    @ManyToOne
    private Turma turma;

    @OneToOne
    private Atividade atividade;

    // Construtor para associar com uma Atividade
    // Uma atividade só tem um Horário
    public Horario(String diaDaSemana, LocalTime horarioInicio, LocalTime horarioFim, Atividade atividade) {
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.atividade = atividade;
        this.recorrencia = "única";
    }

    // Construtor para associar com uma Turma
    public Horario(String diaDaSemana, LocalTime horarioInicio, LocalTime horarioFim, String recorrencia, Turma turma) {
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.turma = turma;
        this.recorrencia = recorrencia;
    }
    

    // Getters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Alocacao getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
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

    public String getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(String recorrencia) {
        this.recorrencia = recorrencia;
    }
}
