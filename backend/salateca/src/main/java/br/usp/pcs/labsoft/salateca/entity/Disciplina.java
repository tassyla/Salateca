package br.usp.pcs.labsoft.salateca.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue
    private int id;

    private String codigo;
    private String nome;

    // Cada disciplina pode ter várias turmas
    // Quando uma disciplina é excluída, as turmas
    // associadas a ela também são excluídas
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private Collection<Turma> turmas;

    // Construtores
    public Disciplina() {}

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // ----------------- Getters e setters -----------------------
    
    public Turma getTurmaByCodigo(String codigo) {
        for (Turma turma : this.turmas) {
            if(turma.getCodigo() == codigo) {
                return turma;
            }
        }
        return null;
    }
    
    
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
    

    // Criar turma e associá-la à disciplina
    public Turma criarTurma(String codigo, Disciplina disciplina,
                List<String> diasDaSemana, List<LocalTime>
                horariosInicios, List<LocalTime> horariosFins, 
                String recorrencia, int quantidadeAlunos, String professor, 
                Date dataInicio, Date dataFim, Boolean acessibilidade,
                Integer quantidadeComputadores, String sistemaOperacional ){

        Turma novaTurma = new Turma(codigo, disciplina, diasDaSemana, horariosInicios, horariosFins, recorrencia, quantidadeAlunos, professor, dataInicio, dataFim, acessibilidade, quantidadeComputadores, sistemaOperacional);
        turmas.add(novaTurma);          
        return novaTurma;
    }

    // Atributos editáveis de Turma:
    // - quantidade de alunos
    // - recorrencia
    // - professor
    // - dataInicio
    // - dataFim
    // - acessibilidade
    // - horários
 
    // Atributos NÃO editáveis de Turma:
    // - código
    // - disciplina
    // - requer computador (quantidadeComputadores, sistemaOperacional)

    public Turma editarTurma(String codigo, Date dataInicio, Date dataFim,
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

    public void deleteTurmabyCodigo(String codigo) {
        Turma turma = getTurmaByCodigo(codigo);
        turmas.remove(turma);
    }


    // Método para listar todas as turmas
    public Collection<Turma> listarTurmas() {
        return turmas;
    }
}
