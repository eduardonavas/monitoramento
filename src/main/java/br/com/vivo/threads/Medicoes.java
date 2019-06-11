package br.com.vivo.threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import br.com.vivo.model.Servidor;

abstract class Medicoes {
	
	private final int QTDE_PING = 5;
	private final char SUCESSO = '1';
	private final char ERRO = '0';
	
	protected long calcTempoMedio(ArrayList<Long> times) {
		long total=0;
		for(Long l : times) {
			//System.out.println(l);
			total += l;
		}
		return (total/times.size());
	}
	
	protected ArrayList<Long> persisteConexao(Servidor servidor) {
		long ini = System.currentTimeMillis();
		
		
			ArrayList<Long> times = new ArrayList<>();
			for(int i=0; i<QTDE_PING; i++) {
				ini=System.currentTimeMillis();
				
				getStatusServidor(servidor);
				
				times.add(System.currentTimeMillis() - ini);
			}
			
			return times;
		
	}
	
	protected char getStatusServidor(Servidor servidor) {
		
		try {
			
			InetSocketAddress socAddress = new InetSocketAddress(servidor.getIp(), servidor.getPorta());
			Socket socket = new Socket();
			socket.connect(socAddress, servidor.getTimeOut());
			
			socket.close();
			
			return SUCESSO;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return ERRO;
		}
	}
}
