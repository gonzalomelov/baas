package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTO implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -6262586479059120338L;

	private long id;
	
	private String email;
	
	private String name;
	
	private String lastname;

	private boolean loggedIn;
	
	//private List<ApplicationDTO> applications = new ArrayList<ApplicationDTO>();

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

//	public List<Application> getApplications() {
//		return applications;
//	}
//
//	public void setApplications(List<Application> applications) {
//		this.applications = applications;
//	}
	
	
}
