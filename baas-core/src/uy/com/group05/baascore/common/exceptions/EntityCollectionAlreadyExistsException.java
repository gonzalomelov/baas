package uy.com.group05.baascore.common.exceptions;

public class EntityCollectionAlreadyExistsException extends Exception {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7302143388023657161L;

	public EntityCollectionAlreadyExistsException(String message) {
		super(message);
	}
}
