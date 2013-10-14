package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "PUSHCHANNELS")
public class PushChannel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	@ManyToOne
	private Application application;
	
	@JoinTable(name = "CLIENTS_PUSHCHANNELS")
	@ManyToMany
	private List<Client> clients = new ArrayList<Client>();

	public PushChannel() {}
	
	public PushChannel(String nombreCanal, Application app) {
		this.name = nombreCanal;
		this.application = app;
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

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		if (!this.clients.contains(client))
			this.clients.add(client);
	}
	
	public void removeClient(Client client) {
		if (this.clients.contains(client))
			this.clients.remove(client);
	}
	
	public boolean hasClient(Client client) {
		return this.clients.contains(client);
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof PushChannel)) return false;
	    PushChannel pc = (PushChannel)o;
	    return this.name.equals(pc.getName());
	}
	
}
