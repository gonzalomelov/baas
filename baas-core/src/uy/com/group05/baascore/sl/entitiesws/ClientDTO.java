package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baascore.common.entities.Role;


public class ClientDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -9089335284964856471L;
	
	private long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String lastname;
	
	private String appName;
	
	private ApplicationDTO application;
	
	private List<SimpleRoleDTO> roles = new ArrayList<SimpleRoleDTO>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}

	public ApplicationDTO getApplication() {
		return application;
	}

	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}

	public List<SimpleRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<SimpleRoleDTO> roles) {
		this.roles = roles;
	}

}
