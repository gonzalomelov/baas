package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.common.exceptions.ApplicationException;
import uy.com.group05.baasadmin.common.exceptions.EntityException;
import uy.com.group05.baasadmin.common.exceptions.RoleException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.PushChannel;
import uy.com.group05.baasadmin.pl.models.Rol;

@ManagedBean(name = "appViewBean")
@ViewScoped
public class AppViewBean {

	private Application app;

	private String entityName;

	private String errorEntity;

	private String rolName;

	private String errorRol;

	private String error;
	
	private String pushChannelsError;
	
	private String pushChannelName;
	
	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public String getErrorRol() {
		return errorRol;
	}

	public void setErrorRol(String errorRol) {
		this.errorRol = errorRol;
	}

	public AppViewBean() {

		if (app == null) {
			
			error = "";

			Map<String, String> parameterMap = (Map<String, String>) FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String paramId = parameterMap.get("id");

			long id = Long.parseLong(paramId);

			ApplicationController appController = new ApplicationController();

			try {
				Application app = appController.GetAplication(id);
				
				setApp(app);
			} catch (ApplicationException e) {
				error = e.getMessage();
			}

		}

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@ManagedProperty(value = "#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;

	public UserSessionManagementBean getUserSessionManagementBean() {
		if (userSessionManagementBean == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			userSessionManagementBean = context.getApplication()
					.evaluateExpressionGet(context,
							"#{userSessionManagementBean}",
							UserSessionManagementBean.class);

		}

		return userSessionManagementBean;
	}

	public void setUserSessionManagementBean(
			UserSessionManagementBean userSessionManagementBean) {
		this.userSessionManagementBean = userSessionManagementBean;
	}

	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public String getErrorEntity() {
		return errorEntity;
	}

	public void setErrorEntity(String errorEntity) {
		this.errorEntity = errorEntity;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String addEntity() {

		CleanErrorMessages();

		if (ExisteEntityEnLista(entityName, app.getEntidades())) {
			errorEntity = "Ya existe la entidad:" + entityName;
			entityName = "";
			return null;
		}

		ApplicationController appController = new ApplicationController();
		try {
			long id = appController
					.addEntity(app.getId(), userSessionManagementBean.getUser()
							.getUserId(), entityName);

			Entity e = new Entity();
			e.setId(id);
			e.setName(entityName);

			app.getEntidades().add(e);
			entityName = "";
		} catch (EntityException e) {
			errorEntity = e.getMessage();
			entityName = "";
		}

		return null;
	}

	public String addRol() {

		CleanErrorMessages();

		if (rolName == null || rolName.equals("")) {
			errorRol = "El nombre no puede ser vacio";
			rolName = "";
			return null;
		}

		if (ExisteRolEnLista(rolName, app.getRoles())) {
			errorRol = "Ya existe el rol:" + rolName;
			rolName = "";
			return null;
		}
		
		try{
			ApplicationController appController = new ApplicationController();
			long id = appController.addRole(userSessionManagementBean.getUser()
							.getUserId(), app.getId(), rolName);
			
			Rol r = new Rol(rolName, id);
			
			app.getRoles().add(r);
		}
		catch(RoleException e){
			errorRol = e.getMessage();
			rolName = "";
			return null;
		}
		

		
		rolName = "";

		return null;
	}
	
	public String addPushChannel() {

		CleanErrorMessages();

		if (pushChannelName == null || pushChannelName.equals("")) {
			errorRol = "El nombre no puede ser vacio";
			pushChannelName = "";
			return null;
		}

		if (ExisteCanalPushEnLista(pushChannelName, app.getPushChannels())) {
			pushChannelsError = "Ya existe el canal:" + pushChannelName;
			pushChannelName = "";
			return null;
		}
		
		try{
			ApplicationController appController = new ApplicationController();
			long id = appController.addPushChannel(app.getId(), pushChannelName);
			
			
			PushChannel p = new PushChannel();
			p.setName(pushChannelName);
			p.setId(id);
			
			app.getPushChannels().add(p);
			
		}
		catch(Exception e){
			pushChannelName = e.getMessage();
			pushChannelName = "";
			return null;
		}
		

		
		pushChannelName = "";

		return null;
	}

	private void CleanErrorMessages() {
		errorEntity = "";
		errorRol = "";
		pushChannelsError = "";
		// error = "";
	}

	private boolean ExisteEntityEnLista(String element, List<Entity> list) {

		boolean retorno = false;

		for (Entity elem : list) {
			if (elem.getName().equals(element)) {
				return true;
			}
		}

		return retorno;
	}

	private boolean ExisteRolEnLista(String element, List<Rol> list) {

		boolean retorno = false;

		for (Rol elem : list) {
			if (elem.getRoleName().equals(element)) {
				return true;
			}
		}

		return retorno;
	}
	
	private boolean ExisteCanalPushEnLista(String element, List<PushChannel> list){
		
		boolean retorno = false;

		for (PushChannel elem : list) {
			if (elem.getName().equals(element)) {
				return true;
			}
		}

		return retorno;
	}

	public String getPushChannelsError() {
		return pushChannelsError;
	}

	public void setPushChannelsError(String pushChannelsError) {
		this.pushChannelsError = pushChannelsError;
	}

	public String getPushChannelName() {
		return pushChannelName;
	}

	public void setPushChannelName(String pushChannelName) {
		this.pushChannelName = pushChannelName;
	}

}
