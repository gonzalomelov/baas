package uy.com.group05.baascore.sl.entitiesws;

public class EntityDTO {
	private long id;
	
	private String name;
	
	private boolean sync;
	
	private ApplicationDTO application;

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

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public ApplicationDTO getApplication() {
		return application;
	}

	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}
	
	
}
