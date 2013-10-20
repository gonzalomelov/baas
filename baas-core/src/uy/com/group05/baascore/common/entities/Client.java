package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "CLIENTS")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String password;
	
	@Column(unique=true)
	private String email;
	
	private String name;
	
	private String lastname;

	private boolean loggedIn;
	
	private UUID accessToken;
	
	private UUID refreshToken;
	
	@ManyToOne
	private Application application;
	
	@OneToMany
	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();

	@ManyToMany(mappedBy = "clients")
	private List<PushChannel> pushChannels = new ArrayList<PushChannel>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UUID getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(UUID accessToken) {
		this.accessToken = accessToken;
	}

	public UUID getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(UUID refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<PushChannel> getPushChannels() {
		return pushChannels;
	}

	public void setPushChannels(List<PushChannel> pushChannels) {
		this.pushChannels = pushChannels;
	}
	
	//+++++++++++++++++++++++++++++++++++//
	@Override
	public boolean equals(Object o){ //Modificar con token de FB
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Client)) return false;
	    Client client = (Client)o;
	    if (this.email.equals(client.getEmail()) && this.name.equals(client.getName()))
	    	return true;
	    else
	    	return false;
	}
	
	public boolean existsRole(long idRole){
		if (this.roles.isEmpty())
			return false;
		Iterator<Role> iter = this.roles.iterator();
		boolean encontre = false;
		while (iter.hasNext() && !encontre){
			Role r = iter.next();
			encontre = (r.getId() == idRole);
		}
		return encontre;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role){
		this.roles.remove(role);
	}
}
