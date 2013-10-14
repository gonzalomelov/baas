package uy.com.group05.baascore.common.exceptions;

public class RoleAlreadyRegisteredException extends Exception {
	/**
	 * UUID 
	 */
	private static final long serialVersionUID = 5842095872751683764L;

	public RoleAlreadyRegisteredException(String message) {
		super(message);
	}
}