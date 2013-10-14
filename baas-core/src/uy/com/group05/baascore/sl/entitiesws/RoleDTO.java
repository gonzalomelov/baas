package uy.com.group05.baascore.sl.entitiesws;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baascore.common.entities.Permission;

public class RoleDTO {
	private long id;
	
	private String name;

	private ApplicationDTO application;

	private List<PermissionDTO> permissions = new ArrayList<PermissionDTO>();
	
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

	public ApplicationDTO getApplication() {
		return application;
	}

	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}

	public List<PermissionDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDTO> permissions) {
		this.permissions = permissions;
	}
	
	
}
