package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import uy.com.group05.baascore.common.entities.Entity;

@XmlRootElement
public class ApplicationDTO implements Serializable {
	
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7446551578846666530L;

	private long id;
	
	private String name;
	
	private String url;
	
	private UUID token;
	
	private List<UserDTO> users = new ArrayList<UserDTO>();
	
	private List<ClientDTO> clients = new ArrayList<ClientDTO>();
	
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	
	private List<EntityDTO> entities = new ArrayList<EntityDTO>();

	public ApplicationDTO() {}
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public List<ClientDTO> getClients() {
		return clients;
	}

	public void setClients(List<ClientDTO> clients) {
		this.clients = clients;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public List<EntityDTO> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityDTO> entities) {
		this.entities = entities;
	}
	
}
