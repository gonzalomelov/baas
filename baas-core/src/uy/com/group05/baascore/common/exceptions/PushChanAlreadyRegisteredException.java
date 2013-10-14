package uy.com.group05.baascore.common.exceptions;

public class PushChanAlreadyRegisteredException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 5120801687338217067L;

	public PushChanAlreadyRegisteredException(String message) {
		super(message);
	}
}
