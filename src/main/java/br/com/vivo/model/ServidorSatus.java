package br.com.vivo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServidorSatus {

	
	private int id;
	private String nome;
	private String hostName;
	private String ip;
	@JsonIgnore
	private String usuario;
	@JsonIgnore
	private String senha;
	private char status;
	private int pingTimeOut;
	private long tempoMedioPing;
	private int tempoRefresh;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String host_name) {
		this.hostName = host_name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public int getPingTimeOut() {
		return pingTimeOut;
	}
	public void setPingTimeOut(int pingTimeOut) {
		this.pingTimeOut = pingTimeOut;
	}
	public long getTempoMedioPing() {
		return tempoMedioPing;
	}
	public void setTempoMedioPing(long tempoMedioPing) {
		this.tempoMedioPing = tempoMedioPing;
	}
	public int getTempoRefresh() {
		return tempoRefresh;
	}
	public void setTempoRefresh(int tempoRefresh) {
		this.tempoRefresh = tempoRefresh;
	}
}
