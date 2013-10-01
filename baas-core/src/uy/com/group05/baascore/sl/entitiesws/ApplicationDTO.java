package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;
import java.util.UUID;

public class ApplicationDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7446551578846666530L;

	private long id;
	
	private String name;
	
	private String url;
	
	private UUID token;
	
	//private List<User> users = new ArrayList<User>();
	
	//private List<Client> clients = new ArrayList<Client>();
	
	//private List<Role> roles = new ArrayList<Role>();
	
	//private List<uy.com.group05.baascore.common.entities.Entity> entities = new ArrayList<uy.com.group05.baascore.common.entities.Entity>();

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

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//
//	public List<Client> getClients() {
//		return clients;
//	}
//
//	public void setClients(List<Client> clients) {
//		this.clients = clients;
//	}
//
//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}
//
//	public List<uy.com.group05.baascore.common.entities.Entity> getEntities() {
//		return entities;
//	}
//
//	public void setEntities(
//			List<uy.com.group05.baascore.common.entities.Entity> entities) {
//		this.entities = entities;
//	}
//	
	
}
