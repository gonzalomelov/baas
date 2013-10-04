package uy.com.group05.baascore.common.exceptions;

public class ClientNotRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 4269938869391314371L;

	public ClientNotRegisteredException(String message) {
		super(message);
	}
}
