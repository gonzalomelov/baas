package uy.com.group05.baasadmin.pl.beans;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Operacion;
import uy.com.group05.baasadmin.pl.models.Rol;

@Named("entityBean")
@RequestScoped
public class EntityBean {

	private Entity entity;
	
	private List<Rol> roleList;
	
	private List<Operacion> operationList;

	

	public List<Rol> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Rol> roleList) {
		this.roleList = roleList;
	}

	public List<Operacion> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operacion> operationList) {
		this.operationList = operationList;
	}
	
	public EntityBean() {

		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String paramAppId = parameterMap.get("appId");
		
		String paramEntityId = parameterMap.get("entityId");

		long appId = Long.parseLong(paramAppId);
		long entityId = Long.parseLong(paramEntityId);

		ApplicationController appController = new ApplicationController();
		
		roleList = appController.getRoles(appId);
		operationList = appController.getOperaciones(appId);
		entity = appController.getEntity(entityId);		

	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
}
