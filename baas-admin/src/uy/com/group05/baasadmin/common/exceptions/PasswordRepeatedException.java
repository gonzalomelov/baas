package uy.com.group05.baasadmin.common.exceptions;

public class PasswordRepeatedException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordRepeatedException(String message) {
		super(message);
	}
	
}