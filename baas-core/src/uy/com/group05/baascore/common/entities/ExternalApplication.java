package uy.com.group05.baascore.common.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXTERNALAPPLICATIONS")
public class ExternalApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long externalAppId;
	
	private String name;

	private UUID clientId;
	
	@JoinTable(name = "APPLICATIONS_EXTERNALAPPLICATIONS")
	@ManyToMany
	private List<Application> applications;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExternalAppId() {
		return externalAppId;
	}

	public void setExternalAppId(long externalAppId) {
		this.externalAppId = externalAppId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	
	
}
