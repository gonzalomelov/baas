package uy.com.group05.baasadmin.pl.beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baasadmin.pl.models.Entity;

@ManagedBean(name="appViewBean")
@ViewScoped
public class AppViewBean {

	private Application app;
	
	private String entityName;
	
	private String errorEntity;

	public AppViewBean() {

		if (app == null) {

			Map<String, String> parameterMap = (Map<String, String>) FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String paramId = parameterMap.get("id");

			long id = Long.parseLong(paramId);

			ApplicationController appController = new ApplicationController();

			setApp(appController.GetAplication(id));

		}

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
		
		if(ExisteEntityEnLista(entityName, app.getEntidades())){
			errorEntity = "Ya existe la entidad:"+ entityName;
			entityName = "";
			return null;
		}
		
		Entity e = new Entity();
		e.setId(app.getEntidades().size() + 1);
		e.setName(entityName);
		
		app.getEntidades().add(e);
		entityName = "";
		
		return null;
	}
	
	private void CleanErrorMessages(){
		errorEntity = "";
		/*errorRol = "" ;
		error = "";*/
	}
	
	private boolean ExisteEntityEnLista(String element, List<Entity> list){
		
		boolean retorno = false;
		
		for (Entity elem : list) {
			if(elem.getName().equals(element)){
				return true;
			}
		}
		
		return retorno;
	}

}
