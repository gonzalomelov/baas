package uy.com.group05.baascore.common.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
}
