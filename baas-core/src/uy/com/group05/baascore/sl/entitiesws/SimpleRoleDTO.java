package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

public class SimpleRoleDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 6619411349475892257L;

	private long id;
	
	private String name;

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
	
	
}
