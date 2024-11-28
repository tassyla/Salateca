package br.usp.pcs.labsoft.salateca.service;

import br.usp.pcs.labsoft.salateca.entity.Horario;
import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.entity.Alocacao;
import br.usp.pcs.labsoft.salateca.entity.RequerComputador;
import br.usp.pcs.labsoft.salateca.entity.Turma;


import br.usp.pcs.labsoft.salateca.repository.SalaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GerenciadorDeSala {

    private final SalaRepository salaRepository;

    public GerenciadorDeSala(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    // Criar nova sala
    public Sala criarSala(String codigo, 
                          int capacidade, 
                          Boolean acessibilidade, 
                          int quantidadeComputadores,
                          String sistemaOperacional, 
                          String tecnicosResponsaveis) {

        if (quantidadeComputadores != 0 && sistemaOperacional != null && tecnicosResponsaveis != null) {
            Sala novaSala = new Sala(codigo, capacidade, acessibilidade, quantidadeComputadores, sistemaOperacional, tecnicosResponsaveis);
            return novaSala;
        }
        
        Sala novaSala = new Sala(codigo, capacidade, acessibilidade);
        return novaSala;

        
        // Salva no banco de dados
        // Sala novaSala = salaRepository.save(sala);
        // return novaSala;
    }


    // Buscar sala pelo código
    public Sala buscarSala(String codigo) {
        return salaRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Sala não encontrada com código: " + codigo));
    }


    // Atualizar sala existente
    public Sala atualizarSala(String codigo, Sala novaSala) {
        Sala salaExistente = buscarSala(codigo);
        salaExistente.setCapacidade(novaSala.getCapacidade());
        salaExistente.setAcessibilidade(novaSala.getAcessibilidade());
        salaExistente.setComputadorSala(novaSala.getComputadorSala());
        return salaRepository.save(salaExistente);
    }

    // Excluir sala
    public void excluirSala(String codigo) {
        Sala sala = buscarSala(codigo);
        salaRepository.delete(sala);
    }

    // Listar todas as salas
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    // -------------------------- MÉTODOS PARA DECIDIR ALOCAÇÃO -------------------------------
    //  Filtra as salas compatíveis com os requisitos dados
    //  Na Classe GerenciadorDeSalas, ter uma função filtra salas 
    //  compatíveis. Recebe os requisitos (quantidade de alunos, acessibilidade, computador), 
    //  tem que iterar na lista de salas existentes e retorna a lista de salas compatíveis
    public List<Sala> getSalasCompativeis(int quantidadeAlunos, boolean acessibilidade, 
                                          RequerComputador requerComputador) {
        
        List<Sala> salasCompativeis = new ArrayList<>();
        List<Sala> salas = listarSalas();

        for(Sala sala : salas) {
            boolean capacidadeOK = sala.getCapacidade() >= quantidadeAlunos;
            boolean acessibilidadeOK = (acessibilidade == false) || (sala.getAcessibilidade() == acessibilidade);
            boolean computadorOK = (requerComputador == null) || (sala.getComputadorSala() != null && 
                                    sala.getComputadorSala().getQuantidadeComputadores() >= requerComputador.getQuantidadeComputadores() &&
                                    sala.getComputadorSala().getSistemaOperacional().equals(requerComputador.getSistemaOperacional()));

            if (capacidadeOK && acessibilidadeOK && computadorOK) {
                salasCompativeis.add(sala);
            }
        }
        return salasCompativeis;
    }

    // Detecta conflito de horários em salas
    // Na Classe Gereneciador de Salas ter uma função
    // detectarConflito , ela recebe um Horário e uma 
    // lista de salas. Vai iterar sobre essa lista de salas
    // e, para cada sala, iterar sobre sua lista de alocações 
    // e verificar se o seu Horário é conflitante com o Horário fornecido
    public List<Map.Entry<Sala, Boolean>> detectarConflitos(Horario horario, List<Sala> salas) {
        List<Map.Entry<Sala, Boolean>> conflitos = new ArrayList<>();

        for (Sala sala : salas) {
            boolean temConflito = false;

            for (Alocacao alocacao : sala.getAlocacoes()) {
                if (temConflitoDeHorario(horario, alocacao.getHorario())) {
                    temConflito = true;
                    break; // Se houver um conflito, não precisamos verificar outras alocações nesta sala
                }
            }

            // Retorna a lista de cada salas com a indicação de existência de conflito
            conflitos.add(new AbstractMap.SimpleEntry<>(sala, temConflito));
        }

        return conflitos;
    }
    //  Compara dois horários para detectar conflitos
    //  Função HorariosConflitantes, recebe dois Horários e
    //  determina se eles configuram conflito. Recebe também 
    //  a data de início e fim dos dois. Deve levar em conta 
    //  primeiro se o dia da semana é igual, depois se as datas
    //  de início e fim se interpolam, depois se os horários de início
    //  e fim se interpolam, depois se as duas são quinzenais, ou uma quinzenal
    //  e outra pontual e se as datas dão conflito
    private boolean temConflitoDeHorario(Horario horario1, Horario horario2) {
        // Se forem em dias da semana diferentes, não há conflito
        if(horario1.getDiaDaSemana() != horario2.getDiaDaSemana()) {
            return false;
        }

        // 
        if (horario1.getDataInicio().isAfter(horario2.getDataFim())) {
            return false;
        }

        if (horario1.getDataFim().isBefore(horario2.getDataInicio())) {
            return false;
        }

        // Se o horário1 começa depois do fim do horário2, não há conflito
        if (horario1.getHorarioInicio().isAfter(horario2.getHorarioFim())) {
            return false;
        }

        // Se o horário1 termina antes do início do horário2, não há conflito
        if (horario1.getHorarioFim().isBefore(horario2.getHorarioInicio())) {
            return false;
        }


        // Se um dos horários é quinzenal e o outro é pontual, e estão na mesma semana, há conflito
        if (horario1.getRecorrencia().equals("quinzenal") && horario2.getRecorrencia().equals("única")) {
            if (isQuinzenalConflict(horario1.getDataInicio(), horario2.getDataInicio())) {
                return true;
            }
            return false;
        }

        if (horario2.getRecorrencia().equals("quinzenal") && horario1.getRecorrencia().equals("única")) {
            if (isQuinzenalConflict(horario2.getDataInicio(), horario1.getDataInicio())) {
                return true;
            }
            return false;
        }

        // Se ambos os horários são quinzenais e caem na mesma semana, há conflito
        if (horario1.getRecorrencia().equals("quinzenal") && horario2.getRecorrencia().equals("quinzenal")) {
            if (isQuinzenalConflict(horario1.getDataInicio(), horario2.getDataInicio())) {
                return true;
            }
            return false;
        }  
        
        // Se um dos horários é semanal, há conflito
        
        return true;
    }

    private boolean isQuinzenalConflict(LocalDate dataQuinzenal1, LocalDate dataQuinzenal2) {

        // Calcular a diferença de semanas
        long weeksDifference = ChronoUnit.WEEKS.between(dataQuinzenal1, dataQuinzenal2);
    
        // Verifica se estão na mesma semana da recorrência quinzenal
        return weeksDifference % 2 == 0 && weeksDifference >= 0;
    }

    // Exibe salas compatíveis e detecta conflitos de horários
    // Essa função vai receber um Horário e vai usar as funções
    // acima pra retomar uma lista com as salas compatíveis e cada uma
    // acompanhada de um booleano conflito
    public List<Map.Entry<Sala, Boolean>> exibirSalasEConflitos(Horario horario) {
        // Pega a turma associada ao horário
        Turma turma = horario.getTurma();

        // Pega todas as salas compatíveis com a turma ( independe do horário)
        List<Sala> salasCompatíveis = getSalasCompativeis(turma.getQuantidadeAlunos(), 
                                                    turma.getAcessibilidade(), 
                                                    turma.getRequerComputador());
                                                    


        return detectarConflitos(horario, salasCompatíveis);
    }
}
