package uy.com.group05.baascore.common.exceptions;

public class MongoDBAlreadyExistsException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 5487693327073900719L;

	public MongoDBAlreadyExistsException(String message) {
		super(message);
	}
}
