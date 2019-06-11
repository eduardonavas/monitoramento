package br.com.vivo.model;

public class Demanda {

	private String demanda;
	private String aplicacao;
	private boolean ativa;
	
	public String getDemanda() {
		return demanda;
	}
	public void setDemanda(String demanda) {
		this.demanda = demanda;
	}
	public String getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
}
