package uy.com.group05.baascore.common.exceptions;

public class RoleNotRegisteredException extends Exception {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 393108105057534015L;

	public RoleNotRegisteredException(String message) {
		super(message);
	}
}
