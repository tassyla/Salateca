package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Turma {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private String nome;

    // Uma turma pode ou não requerer computadores
    @OneToOne(mappedBy = "turma", cascade = CascadeType.ALL)
    private RequerComputador requerComputador;

    // Uma turma está associada a uma disciplina
    @ManyToOne
    private Disciplina disciplina;

    // Uma turma tem obrigatoriamente um ou mais horários
    // Se a turma for excluída, os horários associados a ela também são excluídos
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Horario> horarios;

    public Turma(String codigo, String nome, Disciplina disciplina, Integer quantidadeComputadores, String sistemaOperacional,
                List<String> diasDaSemana, List<LocalTime> horariosInicios, List<LocalTime> horariosFins) {
        this.codigo = codigo;
        this.nome = nome;
        this.disciplina = disciplina;

        // Verifica se há a quantidade correta de elementos 
        // para criar um horário para cada
        if (diasDaSemana.size() != horariosInicios.size() || diasDaSemana.size() != horariosFins.size()) {
            throw new IllegalArgumentException("As listas de dias, horários de início e fim devem ter o mesmo tamanho.");
        }

        // Cria os objetos Horario para cada trio (diaDaSemana, horarioInicio, horarioFim)
        this.horarios = new ArrayList<>();
        for (int i = 0; i < diasDaSemana.size(); i++) {
            Horario horario = new Horario(diasDaSemana.get(i), horariosInicios.get(i), horariosFins.get(i), this);
            this.horarios.add(horario);
        }

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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
