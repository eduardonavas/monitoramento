package br.com.vivo.threads;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IniciarSupervisor {

	@Autowired
	private Supervisor supervisor;
	
	@PostConstruct
	public void iniciar() {

		supervisor.supervisionar();
		
	}
}
