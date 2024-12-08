package br.usp.pcs.labsoft.salateca.dto;

public class CriarSalaDTO {
    private String codigo;
    private int capacidade;
    private Boolean acessibilidade;
    private Integer quantidadeComputadores;
    private String sistemaOperacional;
    private String tecnicosResponsaveis;

    // Getters and setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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

    public String getTecnicosResponsaveis() {
        return tecnicosResponsaveis;
    }

    public void setTecnicosResponsaveis(String tecnicosResponsaveis) {
        this.tecnicosResponsaveis = tecnicosResponsaveis;
    }
}
