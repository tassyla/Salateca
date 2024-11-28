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


    // Construtores
    public Alocacao() {
    }

    // Alocação para atividade
    public Alocacao(Sala sala, Horario horario) {
        this.sala = sala;
        this.horario = horario;
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

}
