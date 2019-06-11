package br.com.vivo.threads;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.vivo.controler.MonitorController;

@Component
public class PararMonitor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@PreDestroy
	public void destroy() {
		logger.info("Parando Monitores");
		MonitorController.removeTodosMonitores();
		logger.info("Monitores parados");
	}
}
