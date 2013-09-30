package uy.com.group05.baascore.common.exceptions;

public class UserNotRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 4269938869391314371L;

	public UserNotRegisteredException(String message) {
		super(message);
	}
}
