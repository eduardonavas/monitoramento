package br.com.vivo.controler;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.vivo.interfaces.IServidorService;
import br.com.vivo.model.MonitorBean;
import br.com.vivo.model.Servidor;
import br.com.vivo.threads.Monitor;

public class MonitorController {

	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	
	private static final ArrayList<MonitorBean> MONITORES = new ArrayList<>();
	
	public static ArrayList<MonitorBean> getMonitores(){
		return MONITORES;
	}
	
	public static void removeTodosMonitores() {
		for(MonitorBean t : MONITORES) {
			if(t.getThread().isAlive()) {
				t.getThread().interrupt();
			}
		}
		
		MONITORES.removeAll(MONITORES);
	}
	
	public static void removeMonitor(Servidor servidor) {
		
			MonitorBean monitor = null;
			for(MonitorBean m : MONITORES) {
				if(m.getMonitor().getServidor().getIp().equals(servidor.getIp())) {
					monitor = m;
				}
			}
			
			if(monitor != null) {
				monitor.getThread().interrupt();
				MONITORES.remove(monitor);
			}
			
			logger.info("Monitor " + servidor.getIp() + " removido");
				
	}
	
	public static void updateMonitor(Servidor servidor) {
		for(MonitorBean mb : MONITORES) {
			if(mb.getMonitor().getServidor().getId() == servidor.getId()) {
				mb.getMonitor().setServidor(servidor);
				logger.info("Servidor " + servidor.getNome() + " atualizado");
			}
		}
	}
	
	
	private static boolean validaMonitorNaoExiste(Servidor servidor) {
		for(MonitorBean monitor : MONITORES) {
			if(servidor.getIp().equals(monitor.getThread().getName())) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void addMonitor(Servidor servidor, IServidorService servService) {
		
		if(validaMonitorNaoExiste(servidor)) {
			
			MonitorBean monitorBean = new MonitorBean();
			Thread thread = new Thread();
			
			monitorBean.setMonitor(new Monitor(servidor, servService));
			monitorBean.setThread(new Thread(monitorBean.getMonitor()));
			monitorBean.getThread().setName(servidor.getIp());
			monitorBean.getThread().start();
			MONITORES.add(monitorBean);
			
			thread.start();
			logger.info("Servidor " + servidor.getNome() + " IP: " + servidor.getIp() + " adicionado ao monitoramento");
		}else {
			updateMonitor(servidor);
		}
		
	}	
}
