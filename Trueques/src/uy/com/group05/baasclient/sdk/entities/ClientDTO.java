package uy.com.group05.baasclient.sdk.entities;

import java.io.Serializable;


public class ClientDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -9089335284964856471L;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String lastname;
	
	private String appName;
	
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
}
