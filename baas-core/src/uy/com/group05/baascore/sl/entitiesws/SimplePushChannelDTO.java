package uy.com.group05.baascore.sl.entitiesws;

import java.util.ArrayList;
import java.util.List;

public class SimplePushChannelDTO {
	private long id;
	
	private String name;

	private ApplicationDTO application;

	private List<ClientDTO> clients = new ArrayList<ClientDTO>();
	
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

	public List<ClientDTO> getClients() {
		return clients;
	}

	public void setClients(List<ClientDTO> clients) {
		this.clients = clients;
	}
	
	
}
