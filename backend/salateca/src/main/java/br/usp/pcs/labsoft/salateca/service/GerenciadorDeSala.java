package br.usp.pcs.labsoft.salateca.service;

import br.usp.pcs.labsoft.salateca.entity.Horario;
import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenciadorDeSala {

    private final SalaRepository salaRepository;

    public GerenciadorDeSala(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    // Criar nova sala
    public Sala criarSala(Sala sala) {
        // Salva no banco de dados
        Sala novaSala = salaRepository.save(sala);
        return novaSala;
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
     // Filtra as salas compatíveis com os requisitos dados
    //  Na Classe GerenciadorDeSalas, ter uma função filtra salas 
    //  compatíveis. Recebe os requisitos (quantidade de alunos, acessibilidade, computador), 
    //  tem que iterar na lista de salas existentes e retorna a lista de salas compatíveis
    public List<Sala> getSalasCompativeis(int quantidadeAlunos, boolean acessibilidade, 
                                          RequerComputador requerComputador) {
        
        List<Sala> salasCompativeis = new ArrayList<>();

        for(Sala sala : salas) {
            boolean capacidadeOk = sala.getCapacidade() >= quantidadeAlunos;
            boolean acessibilidadeOk = sala.getAcessibilidade() == acessibilidade;
            boolean computadorOk = (requerComputador == null) || (sala.getComputador() != null && 
                                    sala.getComputador().getNumeroComputadores() >= requerComputador.getNumeroComputadores() &&
                                    sala.getComputador().getSistemaOperacional().equals(requerComputador.getSistemaOperacional()));

            if (capacidadeOk && acessibilidadeOk && computadorOk) {
                salasCompativeis.add(sala);
            }
        }
        return salasCompativeis;
    }

        
        
        
        
        return listarTodasSalas().stream()
                .filter(sala -> sala.getCapacidade() >= quantidadeAlunos)
                .filter(sala -> sala.getAcessibilidade() == acessibilidade)
                .filter(sala -> sala.getComputadorSala() == computador)
                .collect(Collectors.toList());
    }

    // Detecta conflito de horários em salas
    // Na Classe Gereneciador de Salas ter uma função
    //  detectarConflito , ela recebe um Horário e uma 
    //  lista de salas. Vai iterar sobre essa lista de salas
    //   e, para cada sala, iterar sobre sua lista de alocações 
    //   e verificar se o seu Horário é conflitante com o Horário fornecido
    public boolean detectarConflitos(Horario horario, List<Sala> salas) {
        for (Sala sala : salas) {
            for (Horario alocacao : sala.getAlocacoes()) {
                if (temConflitoDeHorario(horario, alocacao)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Compara dois horários para detectar conflitos
    // Função HorariosConflitantes, recebe dois Horários e
    //  determina se eles configuram conflito. Recebe também 
    //  a data de início e fim dos dois. Deve levar em conta 
    //  primeiro se o dia da semana é igual, depois se as datas
    //   de início e fim se interpolam, depois se os horários de início
    //    e fim se interpolam, depois se as duas são quinzenais, ou uma quinzenal
    //     e outra pontual e se as datas dão conflito
    private boolean temConflitoDeHorario(Horario horario1, Horario horario2) {
        return horario1.getDiaDaSemana().equals(horario2.getDiaDaSemana()) &&
               horario1.getHoraInicio().isBefore(horario2.getHoraFim()) &&
               horario1.getHoraFim().isAfter(horario2.getHoraInicio());
    }

    // Exibe salas compatíveis e detecta conflitos de horários
    // Essa função vai receber um Horário e vai usar as funções a
    //  cima pra retomar uma lista com as salas compatíveis e cada uma
    //  acompanhada de um booleano conflito
    public List<Sala> exibirSalasEConflitos(Horario horario) {
        List<Sala> salasCompatíveis = filtrarSalas(horario.getQuantidadeAlunos(), 
                                                    horario.getAcessibilidade(), 
                                                    horario.getComputadorSala());
        for (Sala sala : salasCompatíveis) {
            boolean conflito = detectarConflitos(horario, salasCompatíveis);
            sala.setConflito(conflito);
        }
        return salasCompatíveis;
    }
}
