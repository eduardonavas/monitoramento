package br.com.vivo.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vivo.interfaces.IServidorService;
import br.com.vivo.model.Servidor;

public class Monitor extends Medicoes implements Runnable {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Servidor servidor = new Servidor();
	
	public Monitor(Servidor servidor, IServidorService servService) {
		this.servidor = servidor;
	}
	
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public Servidor getServidor() {
		return this.servidor;
	}	
	
	private void monitorar() {			
		servidor.setStatus(getStatusServidor(servidor));
		if(servidor.getStatus() == '1') {
			servidor.setTempoMedioResposta(calcTempoMedio(persisteConexao(servidor)));
		}
	}
	
	@Override
	public void run() {
		logger.info("Iniciou monitor " + servidor.getIp());
		while(true) {
			monitorar();
			try {
				Thread.sleep(servidor.getTempoRefresh());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
