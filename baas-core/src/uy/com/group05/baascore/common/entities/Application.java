package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "APPLICATIONS")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	private UUID apiClientId;
	
	@JoinTable(name = "USERS_APPLICATIONS")
	@ManyToMany
	private List<User> users = new ArrayList<User>();
	
	@OneToMany(mappedBy = "application")
	private List<Client> clients = new ArrayList<Client>();
	
	@OneToMany(mappedBy = "application")
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToMany(mappedBy = "application")
	private List<uy.com.group05.baascore.common.entities.Entity> entities
		= new ArrayList<uy.com.group05.baascore.common.entities.Entity>();

	@OneToMany(mappedBy = "application")
	private List<PushChannel> pushChannels = new ArrayList<PushChannel>();
	
	@ManyToMany(mappedBy = "applications")
	private List<ExternalApplication> externalApplications = new ArrayList<ExternalApplication>();
	
	@OneToMany(mappedBy = "application")
	private List<ExternalClient> externalClients = new ArrayList<ExternalClient>();
	
	@OneToMany
	@JoinColumn(name = "APPLICATIONS_EXTERNALCLIENTSROLES", referencedColumnName = "id")
	private List<Role> externalClientsRoles;
	
	public Application() {}
	
	public Application(String nombreApp, User owner) {
		this.name = nombreApp;
		users.add(owner);
	}
	
	public Application(String name, User owner, List<Role> roles, List<Entity> entidades) {
		this.name = name;
		//this.users = new ArrayList<User>();
		users.add(owner);
		this.roles = roles;
		this.entities = entidades;
		//this.clients = new ArrayList<Client>();
	}
	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<uy.com.group05.baascore.common.entities.Entity> getEntities() {
		return entities;
	}

	public void setEntities(
			List<uy.com.group05.baascore.common.entities.Entity> entities) {
		this.entities = entities;
	}
	
	public List<PushChannel> getPushChannels() {
		return pushChannels;
	}

	public void setPushChannels(List<PushChannel> pushChannels) {
		this.pushChannels = pushChannels;
	}

	public List<ExternalApplication> getExternalApplications() {
		return externalApplications;
	}

	public void setExternalApplications(
			List<ExternalApplication> externalApplications) {
		this.externalApplications = externalApplications;
	}

	public List<ExternalClient> getExternalClients() {
		return externalClients;
	}

	public void setExternalClients(List<ExternalClient> externalClients) {
		this.externalClients = externalClients;
	}

	public List<Role> getExternalClientsRoles() {
		return externalClientsRoles;
	}

	public void setExternalClientsRoles(List<Role> externalClientsRoles) {
		this.externalClientsRoles = externalClientsRoles;
	}

	public void addPushChannel(PushChannel pushChannel) {
		if (!this.pushChannels.contains(pushChannel))
			this.pushChannels.add(pushChannel);
	}
	
	public void removePushChannel(PushChannel pushChannel) {
		if (this.pushChannels.contains(pushChannel))
			this.pushChannels.remove(pushChannel);
	}
	
	public PushChannel getPushChannel(String nombreCanal) {
		for (PushChannel pc : this.pushChannels) {
			if (pc.getName().equalsIgnoreCase(nombreCanal))
				return pc;
		}
		
		return null;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Application)) return false;
	    Application app = (Application)o;
	    return this.name.equals(app.getName());
	}
	
}
