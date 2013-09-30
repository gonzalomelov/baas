package uy.com.group05.baascore.common.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -5528658436316293294L;

	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
}
