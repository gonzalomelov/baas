package uy.com.group05.baascore.common.exceptions;

public class AppNotRegisteredException extends Exception{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 4269938869391314371L;

	public AppNotRegisteredException(String message) {
		super(message);
	}
}
