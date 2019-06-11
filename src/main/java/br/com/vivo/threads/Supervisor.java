package br.com.vivo.threads;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vivo.controler.MonitorController;
import br.com.vivo.interfaces.IServidorService;
import br.com.vivo.model.Servidor;

@Component
public class Supervisor{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArrayList<Servidor> servidores;
	
	
	@Autowired
	private IServidorService servService;
		
	private void coletarServidores() {
		this.servidores = servService.buscarTodos();		
	}
	
	@PostConstruct
	public void supervisionar() {
		
		coletarServidores();
		if(servidores != null) {
			
			
			for(Servidor servidor : servidores) {
				MonitorController.addMonitor(servidor, servService);
			}
			
			logger.info("Monitores iniciados");
		}
	}
}
