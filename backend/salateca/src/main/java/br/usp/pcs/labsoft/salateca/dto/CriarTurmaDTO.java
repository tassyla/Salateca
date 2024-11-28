package br.usp.pcs.labsoft.salateca.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CriarTurmaDTO {
    private String codigoDisciplina;
    private String codigo;
    private List<String> diasDaSemana;
    private List<LocalTime> horariosInicios;
    private List<LocalTime> horariosFins;
    private String recorrencia;
    private int quantidadeAlunos;
    private String professor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean acessibilidade;
    private Integer quantidadeComputadores;
    private String sistemaOperacional;

    // Getters and setters
    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<String> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public List<LocalTime> getHorariosInicios() {
        return horariosInicios;
    }

    public void setHorariosInicios(List<LocalTime> horariosInicios) {
        this.horariosInicios = horariosInicios;
    }

    public List<LocalTime> getHorariosFins() {
        return horariosFins;
    }

    public void setHorariosFins(List<LocalTime> horariosFins) {
        this.horariosFins = horariosFins;
    }

    public String getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(String recorrencia) {
        this.recorrencia = recorrencia;
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

    public Integer getQuantidadeComputadores() {
        return quantidadeComputadores;
    }

    public void setQuantidadeComputadores(Integer quantidadeComputadores) {
        this.quantidadeComputadores = quantidadeComputadores;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
}