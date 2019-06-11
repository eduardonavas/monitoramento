package br.com.vivo.threads;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

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
	
	protected ArrayList<Long> persistePing(InetAddress address, int timeout) {
		long ini = System.currentTimeMillis();
		
		try {
			ArrayList<Long> times = new ArrayList<>();
			for(int i=0; i<QTDE_PING; i++) {
				ini=System.currentTimeMillis();
				
				address.isReachable(timeout);
				
				times.add(System.currentTimeMillis() - ini);
			}
			
			return times;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	protected char getStatusServidor(InetAddress address, int timeout) {
		
		try {
			return address.isReachable(timeout) ? SUCESSO : ERRO ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return '0';
		}
		
		
		
	}
}
