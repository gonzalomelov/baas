package uy.com.group05.baascore.common.exceptions;

public class UsernameAlreadyRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -5968022744856122913L;

	public UsernameAlreadyRegisteredException(String message) {
		super(message);
	}
}
