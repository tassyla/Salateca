import java.util.Collection;

public class Sala {
  private String codigo;
  private int capacidade;
  private Collection<Alocacao> alocacoes;
  

  public Sala(String codigo, int capacidade) {
    this.codigo = codigo;
    this.capacidade = capacidade;
  }

  public void alocarSalaAtividade(String data, String horarioInicio, String horarioFim) {
    Alocacao alocacao = new Alocacao(data, horarioInicio, horarioFim);
    this.alocacoes.add(alocacao);
  }

  public Boolean getConflitoAtividade(String data, String horarioInicio, String horarioFim){
    // TODO
    return true; 
  }

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

  public Collection<Alocacao> getAlocacoes() {
    return alocacoes;
  }

  public void setAlocacoes(Collection<Alocacao> alocacoes) {
    this.alocacoes = alocacoes;
  }

}
