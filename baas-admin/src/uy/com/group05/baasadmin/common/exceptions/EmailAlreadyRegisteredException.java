package uy.com.group05.baasadmin.common.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
}
