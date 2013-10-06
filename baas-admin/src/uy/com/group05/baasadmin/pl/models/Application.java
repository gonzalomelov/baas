package uy.com.group05.baasadmin.pl.models;

public class Application {
	
	private long Id;
	
	private String Name;
	
	public Application() {
		
	};
	
	public Application(String name, long id){
		this.Name = name;
		this.Id = id;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
