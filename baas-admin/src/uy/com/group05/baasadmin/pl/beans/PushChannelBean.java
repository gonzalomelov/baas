package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import uy.com.group05.baasadmin.common.exceptions.ClientRolException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.controllers.ClientController;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.PushChannel;
import uy.com.group05.baasadmin.pl.models.PushChannelEntity;
import uy.com.group05.baascore.sl.services.soap.RolesClientDTO;
import uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO;
import uy.com.group05.baascore.sl.services.soap.SimplePushChannelEntityDTO;

@ManagedBean(name = "pushChannelBean")
@ViewScoped
public class PushChannelBean {
	
	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
	private long appId;	
	
	private long pushChannel;
	
	private String error = "";
	
	private List<String> permisos;
	
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

	public long getPushChannel() {
		return pushChannel;
	}

	public void setPushChannel(long pushChannel) {
		this.pushChannel = pushChannel;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
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
				permisos = new ArrayList<String>();
				permisos.add("");
				
				appId = Long.parseLong(paramAppId);
				pushChannel = Long.parseLong(paramPushChannelId);
	
				ApplicationController appController = new ApplicationController();
	
				entities = appController.GetAplication(appId).getEntidades();
				pushChannelEntities = appController.getEntitiesAssociatedWithPushChannel(appId, pushChannel);
				
				datos = new PushChannelEntity[entities.size()];
				datosVista = new Boolean[entities.size()];
				
				for (int i = 0; i < entities.size(); i++) {
					PushChannelEntity pushChannelEntity = new PushChannelEntity();
					pushChannelEntity.setEntityId(entities.get(i).getId());
					pushChannelEntity.setPushChannelId(pushChannel);
					
					pushChannelEntity.setAssociated(false);
					datosVista[i] = false;
					
					for (PushChannelEntity p: pushChannelEntities) {
						if (p.getEntityId() == entities.get(i).getId() &&
							p.getPushChannelId() == pushChannel) {
							
							pushChannelEntity.setAssociated(true);
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
	
	public String changeEntityAsociationAjax(AjaxBehaviorEvent event) {
		try {
			// printArray();

			int row = (Integer) event.getComponent().getAttributes().get("row");

			datos[row].setAssociated(!datos[row].isAssociated());

			cloneArray();

		} catch (Exception e) {

		}

		return null;
	}
	
	public String submit() {
		try{
			error = "";
			
			ApplicationController applicationController = new ApplicationController();
			
			List<SimplePushChannelEntityDTO> newEntities = new ArrayList<SimplePushChannelEntityDTO>();
			
			for (int i = 0; i < entities.size(); i++) {	
				SimplePushChannelEntityDTO r = new SimplePushChannelEntityDTO();
				r.setId(entities.get(i).getId());
				r.setAssociated(datos[i].isAssociated());
				
				newEntities.add(r);
			}
			
			applicationController.savePushChannelEntities(appId, pushChannel, newEntities);
			
		
			return "/pages/App/Index.xhtml?faces-redirect=true&id=" + appId;
		
		
		} catch (Exception e) {
			error = e.getMessage();
			return "";
		}
		
		
	}
	
	private void cloneArray() {
		
		for (int i = 0; i < entities.size(); i++) {
			datosVista[i] = datos[i].isAssociated();
		}
	}
}