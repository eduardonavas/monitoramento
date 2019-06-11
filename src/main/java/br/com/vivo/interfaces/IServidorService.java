package br.com.vivo.interfaces;

import java.util.ArrayList;

import br.com.vivo.model.Servidor;

public interface IServidorService {

	public void cadastrar(Servidor servidor);
	public void update(Servidor servidor);
	public void delete(Servidor servidor);
	public Servidor buscarPorId(int id);
	public Servidor buscarPorIp(String ip);
	public ArrayList<Servidor> buscarTodos();
}
