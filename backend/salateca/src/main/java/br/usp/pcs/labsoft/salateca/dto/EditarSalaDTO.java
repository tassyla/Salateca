package br.usp.pcs.labsoft.salateca.dto;

public class EditarSalaDTO {
    private int capacidade;
    private Boolean acessibilidade;
    private int quantidadeComputadores;
    private String sistemaOperacional;
    private String tecnicoResponsavel;

    // Getters e Setters
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Boolean acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

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
}
