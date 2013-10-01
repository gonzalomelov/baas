package uy.com.group05.baascore.sl.entitiesws;


public class UserDTO {

	private long id;
	
	private String username;
	
	private String email;
	
	private String name;
	
	private String lastname;

	private boolean loggedIn;
	
	//private List<Application> applications = new ArrayList<Application>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
