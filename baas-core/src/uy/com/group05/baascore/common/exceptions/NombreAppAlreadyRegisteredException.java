package uy.com.group05.baascore.common.exceptions;

public class NombreAppAlreadyRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 4269938869391314371L;

	public NombreAppAlreadyRegisteredException(String message) {
		super(message);
	}
}