package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;
import java.util.UUID;

public class SimpleApplicationDTO implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -2592553627874363363L;

	private long id;
	
	private String name;
	
	private UUID apiClientId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getApiClientId() {
		return apiClientId;
	}

	public void setApiClientId(UUID apiClientId) {
		this.apiClientId = apiClientId;
	}
	
}
