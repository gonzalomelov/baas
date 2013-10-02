package uy.com.group05.baascore.common.exceptions;

public class EmailNotRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -3970191083666754441L;

	public EmailNotRegisteredException(String message) {
		super(message);
	}
}
