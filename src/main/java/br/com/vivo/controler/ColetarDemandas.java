package br.com.vivo.controler;

import java.util.ArrayList;

import br.com.vivo.model.Demanda;
import br.com.vivo.model.Servidor;

public class ColetarDemandas {


	private final String LISTAR_APLICACOES = "ls /usr/GlassfishAppServer/domains/domain1/applications/j2ee-modules";
	private final String LISTAR_DEMANDAS = "cat /home/A0088869/centralTeste.properties";

	private ArrayList<String> aplicacoes = new ArrayList<>();
	private String ip;
	
	private ArrayList<Demanda> demandas = new ArrayList<>();
			
	public ArrayList<Demanda> coletar(Servidor servidor80, Servidor servidor) {
		
		this.ip = servidor.getIp();
		
		ComandosSSH comandos = new ComandosSSH();
		
		System.out.println(servidor80.getIp());
		System.out.println(servidor80.getUsuario());
		System.out.println(servidor80.getSenha());
		comandos.buildConnection(servidor);
		aplicacoes = comandos.executar(LISTAR_APLICACOES);
		comandos.desconect();
				
		comandos.buildConnection(servidor80);
		trataLinha(comandos.executar(LISTAR_DEMANDAS));
		comandos.desconect();
		
		return demandas;

	}
	
	private void trataLinha(ArrayList<String> linhas) {
		
		for(String linha : linhas) {
			if(linha.contains(ip)) {
				Demanda demanda = new Demanda();
				demanda.setDemanda(linha.split("=")[0]);
				demanda.setAplicacao(linha.split("/")[3]);
				demanda.setAtiva(verificaAplicacaiAtiva(demanda.getAplicacao()));
				demandas.add(demanda);
			}
		}		
	}
	
	private boolean verificaAplicacaiAtiva(String aplicacao) {
		return aplicacoes.contains(aplicacao);
	}

	
	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		
		servidor.setIp("10.129.176.28");
		servidor.setUsuario("A0088869");
		servidor.setSenha("vivo2019");
		Servidor servidor80 = new Servidor();
		
		servidor80.setIp("10.129.176.28");
		servidor80.setUsuario("A0088869");
		servidor80.setSenha("vivo2019");
		ColetarDemandas c = new ColetarDemandas();
		c.coletar(servidor80,servidor);
		
		for(Demanda d : c.demandas) {
			System.out.println("Numero demanda: "+d.getDemanda());
			System.out.println("Aplicacoes: "+d.getAplicacao());
			System.out.println(" "+d.isAtiva());
			System.out.println("########");
		}
	}
}
