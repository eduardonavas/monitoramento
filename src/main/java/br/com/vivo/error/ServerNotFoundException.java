package br.com.vivo.error;

public class ServerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServerNotFoundException(String message) {
		super(message);
	}
}
