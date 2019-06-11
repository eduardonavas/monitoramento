package br.com.vivo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TesteSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			InetSocketAddress socAddress = new InetSocketAddress("10.11.191.80", 9002);  
			Socket socket = new Socket();
			
			socket.connect(socAddress,1000);
			System.out.println("conectou");
			socket.close();
			System.out.println("fechou a conexao");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
