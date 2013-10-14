package uy.com.group05.baascore.common.exceptions;

public class EntityCollectionNotRegisteredException extends Exception {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 393108105057534015L;

	public EntityCollectionNotRegisteredException(String message) {
		super(message);
	}
}
