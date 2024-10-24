import java.util.Collection;

public class Controller {
  private Alocador a;
  
  public Controller(Alocador Alocador) {
    this.a = Alocador;
  }

  public Collection<Sala> getSalasCompativeis(int capacidade) {
    Collection<Sala> salasCompativeis = this.a.predio.getSalasCompativeis(capacidade);
  }

  public alocarSalaAtividade(Sala sala, String data, String horaInicio, String horaFim) {
    sala.alocarSalaAtividade(sala, data, horaInicio, horaFim);
  }
  

}