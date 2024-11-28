package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Collection;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private String nome;

    // Cada disciplina pode ter várias turmas
    // Quando uma disciplina é excluída, as turmas associadas também são excluídas
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Turma> turmas = new ArrayList<>();

    // Construtores
    public Disciplina() {}

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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

    public Collection<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Collection<Turma> turmas) {
        this.turmas = turmas;
    }

    // ----------------- Métodos específicos -----------------------

    // Criar turma e associá-la à disciplina
    public Turma criarTurma(String codigo, 
                            List<String> diasDaSemana, 
                            List<LocalTime> horariosInicios, 
                            List<LocalTime> horariosFins, 
                            String recorrencia, 
                            int quantidadeAlunos, 
                            String professor, 
                            LocalDate dataInicio, 
                            LocalDate dataFim, 
                            Boolean acessibilidade,
                            Integer quantidadeComputadores, 
                            String sistemaOperacional) {

        // Cria a nova turma e já associa a esta disciplina
        Turma novaTurma = new Turma(codigo, this, diasDaSemana, horariosInicios, horariosFins, recorrencia,
                quantidadeAlunos, professor, dataInicio, dataFim, acessibilidade, quantidadeComputadores, sistemaOperacional);

        this.turmas.add(novaTurma);
        return novaTurma;
    }

    // Buscar turma pelo código
    public Turma getTurmaByCodigo(String codigo) {
        for (Turma turma : this.turmas) {
            if (turma.getCodigo().equals(codigo)) {
                return turma;
            }
        }
        return null;
    }

    // Editar atributos editáveis da turma
    public Turma editarTurma(String codigo, LocalDate dataInicio, LocalDate dataFim,
                             int quantidadeAlunos, Boolean acessibilidade,
                             String professor, String recorrencia,
                             List<String> diasDaSemana, List<LocalTime> horariosInicios,
                             List<LocalTime> horariosFins) {

        Turma turma = getTurmaByCodigo(codigo);
        if (turma != null) {
            turma.setQuantidadeAlunos(quantidadeAlunos);
            turma.setProfessor(professor);
            turma.setDataInicio(dataInicio);
            turma.setDataFim(dataFim);
            turma.setAcessibilidade(acessibilidade);
            turma.setHorarios(diasDaSemana, horariosInicios, horariosFins, recorrencia);
        }
        return turma;
    }

    // Excluir turma pelo código
    public void deletarTurmaByCodigo(String codigo) {
        Turma turma = getTurmaByCodigo(codigo);
        if (turma != null) {
            this.turmas.remove(turma);
        }
    }

    // Listar todas as turmas
    public Collection<Turma> listarTurmas() {
        return turmas;
    }
}
