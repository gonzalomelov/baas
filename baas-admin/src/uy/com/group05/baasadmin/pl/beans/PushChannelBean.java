package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.controllers.ClientController;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.PushChannel;
import uy.com.group05.baasadmin.pl.models.PushChannelEntity;
import uy.com.group05.baasadmin.pl.models.RoleCliente;
import uy.com.group05.baascore.sl.services.soap.RoleDTO;

public class PushChannelBean {
	
	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
	private long appId;	
	
	private String error = "";
	
	private PushChannel pushChannel;
	
	private List<Entity> entities;
	
	private List<PushChannelEntity> pushChannelEntities;
	
	private PushChannelEntity[] datos;

	private Boolean[] datosVista;

	public UserSessionManagementBean getUserSessionManagementBean() {
		return userSessionManagementBean;
	}

	public void setUserSessionManagementBean(
			UserSessionManagementBean userSessionManagementBean) {
		this.userSessionManagementBean = userSessionManagementBean;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public PushChannel getPushChannel() {
		return pushChannel;
	}

	public void setPushChannel(PushChannel pushChannel) {
		this.pushChannel = pushChannel;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<PushChannelEntity> getPushChannelEntities() {
		return pushChannelEntities;
	}

	public void setPushChannelEntities(List<PushChannelEntity> pushChannelEntities) {
		this.pushChannelEntities = pushChannelEntities;
	}

	public PushChannelEntity[] getDatos() {
		return datos;
	}

	public void setDatos(PushChannelEntity[] datos) {
		this.datos = datos;
	}

	public Boolean[] getDatosVista() {
		return datosVista;
	}

	public void setDatosVista(Boolean[] datosVista) {
		this.datosVista = datosVista;
	}
	
	public PushChannelBean() {
		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		
		String paramAppId = parameterMap.get("appId");
		String paramPushChannelId = parameterMap.get("pushChannelId");
		
		if (paramAppId != null && paramPushChannelId != null) {
			
			try{
				
				appId = Long.parseLong(paramAppId);
				long pushChannelId = Long.parseLong(paramPushChannelId);
	
				ApplicationController appController = new ApplicationController();
	
				entities = appController.GetAplication(appId).getEntidades();
				pushChannelEntities = appController.getEntitiesAssociatedWithPushChannel(appId, pushChannelId);
				
				datos = new PushChannelEntity[entities.size()];
				datosVista = new Boolean[entities.size()];
				
				for (int i = 0; i < entities.size(); i++) {
					PushChannelEntity pushChannelEntity = new PushChannelEntity();
					pushChannelEntity.setEntityId(entities.get(i).getId());
					pushChannelEntity.setPushChannelId(pushChannelId);
					
					pushChannelEntity.setAssociated(false);
					datosVista[i] = false;
					
					for (PushChannelEntity p: pushChannelEntities) {
						if (p.getEntityId() == entities.get(i).getId() &&
							p.getPushChannelId() == pushChannelId) {
							
							pushChannelEntity.setAssociated(false);
							datosVista[i] = true;
							break;
						} 
					}
	
					datos[i] = pushChannelEntity;
				}
			}
			catch(Exception e){
				error = e.getMessage();
				return;
			}

			
		}
	}
}