package br.com.vivo.controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import br.com.vivo.model.Servidor;

public class ComandosSSH {

	
	private final String CHANNEL_TYPE = "exec";
	private final int PORTA = 22;
	private final int TIME_OUT = 10000;
	private Session session;
	
	public void buildConnection(Servidor servidor) {
		try {
			session = new JSch().getSession(servidor.getUsuario(), servidor.getIp(), PORTA);
			session.setPassword(servidor.getSenha());
			
			Properties config = new Properties();
			
			config.put("StrictHostKeyChecking", "no");
			
			session.setConfig(config);
			session.connect(TIME_OUT);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desconect() {
		if(session != null && session.isConnected()) {
			session.disconnect();
		}
	}
		
	public ArrayList<String> executar(String comando) {
		ArrayList<String> linhas = new ArrayList<>();
		ChannelExec channel = null;
		try {
			
						
			channel = (ChannelExec) session.openChannel(CHANNEL_TYPE);
			channel.setCommand(comando);
			channel.connect();
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			
			String linha = "";
						
			while((linha = buffer.readLine()) != null) {
				linhas.add(linha);
			}
					
		} catch (JSchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(channel != null && channel.isConnected()) {
				channel.disconnect();
			}
		}
		
		return linhas;
	}
}
