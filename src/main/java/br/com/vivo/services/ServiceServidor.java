package br.com.vivo.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.controler.ColetarDemandas;
import br.com.vivo.controler.MonitorController;
import br.com.vivo.error.ServerNotFoundException;
import br.com.vivo.interfaces.IServidorService;
import br.com.vivo.model.Demanda;
import br.com.vivo.model.MonitorBean;
import br.com.vivo.model.Servidor;

@RestController
public class ServiceServidor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IServidorService iServidorService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public void cadastrar(@RequestBody Servidor servidor) {
		iServidorService.cadastrar(servidor);
		MonitorController.addMonitor(servidor, iServidorService);
		logger.info("Servidor " + servidor.getIp() + " cadastrado");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	public void update(@RequestBody Servidor servidor) {
		iServidorService.update(servidor);
		MonitorController.updateMonitor(servidor);
		logger.info("Servidor " + servidor.getIp() + " Atualizado");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "*")
	public boolean delete(@PathVariable int id) throws ServerNotFoundException {
		Servidor servidor = buscarPorId(id);
		MonitorController.removeMonitor(servidor);
		iServidorService.delete(servidor);
		return true;
		
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ArrayList<Servidor> buscarTodos() {
		return iServidorService.buscarTodos();
		
	}
	
	@RequestMapping(value = "/getStatusServidores", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ArrayList<Servidor> getStatusServidores() {
		
		ArrayList<Servidor> servidores = new ArrayList<>();
		
		for(MonitorBean mb : MonitorController.getMonitores()) {
			servidores.add(mb.getMonitor().getServidor());
		}
				
		return servidores;
		
		
	}
	
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public Servidor buscarPorId(@PathVariable int id) throws ServerNotFoundException {
		Servidor servidor = iServidorService.buscarPorId(id);
		if(servidor == null) {
			throw new ServerNotFoundException("Servidor id " + id + " nao encontrado.");
		}
		return servidor;
	}
	
	@RequestMapping(value = "/buscarPorIp/{ip}", method = RequestMethod.GET)
	public Servidor buscarPorIp(@PathVariable String ip) throws ServerNotFoundException {
		Servidor servidor = iServidorService.buscarPorIp(ip);
		if(servidor == null) {
			throw new ServerNotFoundException("Servidor id " + ip + " nao encontrado.");
		}
		return servidor;
	}
	
	@RequestMapping(value = "/buscarDemandas/{ip}", method = RequestMethod.GET  )
	@CrossOrigin(origins = "*")
	public ArrayList<Demanda> buscarDemanadas(@PathVariable String ip) throws ServerNotFoundException {
	
		Servidor servidor80 = buscarPorIp("10.129.176.28");
		Servidor servidor = buscarPorIp(ip);
		ColetarDemandas coletarDemandas = new ColetarDemandas();			
		
		return coletarDemandas.coletar(servidor80, servidor);
	}
}
