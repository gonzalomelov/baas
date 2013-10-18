package uy.com.group05.baascore.common.exceptions;

public class EntityNotRegisteredException extends Exception {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 393108105057534015L;

	public EntityNotRegisteredException(String message) {
		super(message);
	}
}
