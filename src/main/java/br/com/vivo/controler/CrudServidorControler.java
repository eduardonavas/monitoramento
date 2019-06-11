package br.com.vivo.controler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivo.interfaces.IServidorService;
import br.com.vivo.interfaces.ServidorRepository;
import br.com.vivo.model.Servidor;

@Service
public class CrudServidorControler implements IServidorService {

	@Autowired
	private ServidorRepository servidorRepo;

	@Override
	public void cadastrar(Servidor servidor) {
		servidorRepo.save(servidor);
		
	}

	@Override
	public Servidor buscarPorId(int id)  {
		
		Servidor servidor = servidorRepo.findById(id);
		
		return servidor;
	}

	@Override
	public ArrayList<Servidor> buscarTodos() {
		
		//List<Servidor> listaServ
		return (ArrayList<Servidor>) servidorRepo.findAll();
		
		//return servidores;
	}

	@Override
	public void update(Servidor servidor) {
		servidorRepo.save(servidor);
		MonitorController.updateMonitor(servidor);
	}

	@Override
	public void delete(Servidor servidor) {
		servidorRepo.delete(servidor);
		
	}

	@Override
	public Servidor buscarPorIp(String ip) {
		
		Servidor servidor = servidorRepo.findByIp(ip);
		
		return servidor;
	}	
}
