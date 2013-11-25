package uy.com.group05.baascore.sl.entitiesws;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.PushChannel;

public class SimpleEntityDTO {
	private long id;
	
	private String name;
	
	private boolean sync;
	
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
	
	
}
