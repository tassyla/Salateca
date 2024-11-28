package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Sala {
    @Id
    private String codigo;
    private int capacidade;
    private Boolean acessibilidade;

    // Cada sala pode ou não ter uma cofiguração de computador
    // Quando uma sala for excluída, o objeto de configuração 
    // de computador (ComputadorSala) também será excluído
    @OneToOne(cascade = CascadeType.ALL)
    private ComputadorSala computadorSala;

    // Cada sala pode ter nenhuma ou várias alocações
    // Quando uma sala for excluída, as suas alocações também serão excluídas
    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Alocacao> alocacoes = new ArrayList<>();

    // Construtor vazio para o JPA utilziar
    // quand instanciar objetos recuperados do banco de dados
    public Sala() {
    }

    // Construtor para criar novas instâncias manualmente

    // Sala sem cpmputador
    public Sala(String codigo, 
    int capacidade,
     Boolean acessibilidade) {
        this.codigo = codigo;
        this.capacidade = capacidade;
        this.acessibilidade = acessibilidade;
        
    }

    // Sala com computador
    public Sala(String codigo, 
    int capacidade, 
    Boolean acessibilidade, 
    int quantidadeComputadores, 
    String sistemaOperacional, 
    String tecnicoResponsavel) {
        this.codigo = codigo;
        this.capacidade = capacidade;
        this.acessibilidade = acessibilidade;

        this.computadorSala = new ComputadorSala(quantidadeComputadores, sistemaOperacional, tecnicoResponsavel, this);
        
    }

    // Métodos para alocar
    public Alocacao alocarSala(Horario horario) {
        Alocacao alocacao = new Alocacao(this, horario);
        this.alocacoes.add(alocacao);
        return alocacao;
    }

    // Getters e setters
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

    public ComputadorSala getComputadorSala() {
        return computadorSala;
    }

    public void setComputadorSala(ComputadorSala computadorSala) {
        this.computadorSala = computadorSala;
    }

    public List<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public int getQuantidadeComputadores() {
        if (this.computadorSala == null) {
            return 0;
        }
        return this.computadorSala.getQuantidadeComputadores();
    }

    public String getSistemaOperacional() {
        if (this.computadorSala == null) {
            return null;
        }
        return this.computadorSala.getSistemaOperacional();
    }

    public String getTecnicoResponsavel() {
        if (this.computadorSala == null) {
            return null;
        }
        return this.computadorSala.getTecnicoResponsavel();
    }

}
