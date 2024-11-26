package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

@Entity
public class Alocacao {
    @Id
    @GeneratedValue
    private int id;

    // Cada alocação é criada por e para uma sala
    @ManyToOne
    private Sala sala;

    // Cada alocação é feita em um horário
    // Quando uma alocação é excluída, o objeto
    // Horario não é excluído
    @OneToOne
    private Horario horario;

    // A alocação pode ser feita para uma atividade
    @ManyToOne
    private Atividade atividade;

    // Ou para uma turma
    @ManyToOne
    private Turma turma;

    // Construtores
    public Alocacao() {
    }

    // Alocação para atividade
    public Alocacao(Sala sala, Horario horario, Atividade atividade) {
        this.sala = sala;
        this.horario = horario;
        this.atividade = atividade;
    }

    // Alocação para turma
    public Alocacao(Sala sala, Horario horario, Turma turma) {
        this.sala = sala;
        this.horario = horario;
        this.turma = turma;
    }

    // ----------------- Getters e setters -----------------------
    public int getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Turma getTurma() {
        return turma;
    }
}
