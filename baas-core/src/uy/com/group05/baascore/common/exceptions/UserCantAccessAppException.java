package uy.com.group05.baascore.common.exceptions;

public class UserCantAccessAppException extends Exception{
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	public UserCantAccessAppException(String message) {
		super(message);
	}
}
