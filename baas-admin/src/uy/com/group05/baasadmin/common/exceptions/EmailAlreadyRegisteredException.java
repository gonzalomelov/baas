package uy.com.group05.baasadmin.common.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
}
