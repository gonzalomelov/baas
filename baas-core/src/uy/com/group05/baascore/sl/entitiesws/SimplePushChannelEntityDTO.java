package uy.com.group05.baascore.sl.entitiesws;

public class SimplePushChannelEntityDTO {
	private long id;
	
	private String name;
	
	private boolean associated;
	
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

	public boolean isAssociated() {
		return associated;
	}

	public void setAssociated(boolean associated) {
		this.associated = associated;
	}
	
}
