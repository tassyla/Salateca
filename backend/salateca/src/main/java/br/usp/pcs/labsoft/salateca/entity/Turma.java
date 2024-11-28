package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
public class Turma {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private int quantidadeAlunos;
    private String professor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean acessibilidade;

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

    // turma não exige computador
    public Turma(String codigo, Disciplina disciplina,
                List<String> diasDaSemana, List<LocalTime> 
                horariosInicios, List<LocalTime> horariosFins, 
                String recorrencia, int quantidadeAlunos, String professor, 
                LocalDate dataInicio, LocalDate dataFim, Boolean acessibilidade) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.quantidadeAlunos = quantidadeAlunos;
        this.professor = professor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.acessibilidade = acessibilidade;


        // Verifica se há a quantidade correta de elementos 
        // para criar um horário para cada

        if (diasDaSemana.size() != horariosInicios.size() || diasDaSemana.size() != horariosFins.size()) {
            throw new IllegalArgumentException("As listas de dias, horários de início e fim devem ter o mesmo tamanho.");
        }

        // Cria os objetos Horario para cada trio (diaDaSemana, horarioInicio, horarioFim)
        this.horarios = new ArrayList<>();
        for (int i = 0; i < diasDaSemana.size(); i++) {
            Horario horario = new Horario(diasDaSemana.get(i), horariosInicios.get(i), horariosFins.get(i), recorrencia,  this);
            this.horarios.add(horario);
        }
    }

     // Turma exige computador
    public Turma(String codigo, Disciplina disciplina,
                List<String> diasDaSemana, List<LocalTime>
                horariosInicios, List<LocalTime> horariosFins, 
                String recorrencia, int quantidadeAlunos, String professor, 
                LocalDate dataInicio, LocalDate dataFim, Boolean acessibilidade,
                Integer quantidadeComputadores, String sistemaOperacional ){
                    
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.quantidadeAlunos = quantidadeAlunos;
        this.professor = professor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.acessibilidade = acessibilidade;

        // Verifica se há a quantidade correta de elementos 
        // para criar um horário para cada
        if (diasDaSemana.size() != horariosInicios.size() || diasDaSemana.size() != horariosFins.size()) {
            throw new IllegalArgumentException("As listas de dias, horários de início e fim devem ter o mesmo tamanho.");
        }

        // Cria os objetos Horario para cada trio (diaDaSemana, horarioInicio, horarioFim)
        setHorarios(diasDaSemana, horariosInicios, horariosFins, recorrencia);

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

    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Boolean acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    // ----------------- Métodos auxiliares -----------------------
    public void setHorarios(List<String> diasDaSemana, List<LocalTime> horariosInicios, List<LocalTime> horariosFins, String recorrencia) {
        this.horarios = new ArrayList<>();
        for (int i = 0; i < diasDaSemana.size(); i++) {
            Horario horario = new Horario(diasDaSemana.get(i), horariosInicios.get(i), horariosFins.get(i), recorrencia,  this);
            this.horarios.add(horario);
        }
    }
    
}
