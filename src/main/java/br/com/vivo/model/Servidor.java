package br.com.vivo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity()
@Table(schema = "IVR_OWNER", name = "SERVIDOR")
public class Servidor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVIDOR")
	@SequenceGenerator(name = "SEQ_SERVIDOR", schema = "IVR_OWNER", allocationSize = 1, sequenceName = "SEQ_SERVIDOR")
	private int id;
	private String nome;
	@Column(name = "HOST_NAME")
	private String hostName;
	private String ip;
	private int porta;
	@Column(name = "TIMEOUT")
	private int timeOut;
	@Transient
	private long tempoMedioResposta;
	private String usuario;
	private String senha;
	private char status;
	@Column(name = "TEMPO_REFRESH")
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
	public int getTempoRefresh() {
		return tempoRefresh;
	}
	public void setTempoRefresh(int tempoRefresh) {
		this.tempoRefresh = tempoRefresh;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public long getTempoMedioResposta() {
		return tempoMedioResposta;
	}
	public void setTempoMedioResposta(long tempoMedioResposta) {
		this.tempoMedioResposta = tempoMedioResposta;
	}
}
