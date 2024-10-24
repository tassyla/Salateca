import java.util.Collection;

public class Alocacao{
  private String data;
  private String horarioInicio;
  private String horarioFim;

  public Alocacao(  String data, String horarioInicio, String horarioFim) {
    this.data = data;
    this.horarioInicio = horarioInicio;
    this.horarioFim = horarioFim;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getHorarioInicio() {
    return horarioInicio;
  }

  public void setHorarioInicio(String horarioInicio) {
    this.horarioInicio = horarioInicio;
  }

  public String getHorarioFim() {
    return horarioFim;
  }

  public void setHorarioFim(String horarioFim) {
    this.horarioFim = horarioFim;
  }

}
