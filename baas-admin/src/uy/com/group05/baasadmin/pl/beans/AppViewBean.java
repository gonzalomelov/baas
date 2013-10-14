package uy.com.group05.baasadmin.pl.beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.common.exceptions.EntityException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Rol;

@ManagedBean(name = "appViewBean")
@ViewScoped
public class AppViewBean {

	private Application app;

	private String entityName;

	private String errorEntity;

	private String rolName;

	private String errorRol;

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

		Rol r = new Rol(rolName, app.getRoles().size() + 1);

		app.getRoles().add(r);
		rolName = "";

		return null;
	}

	private void CleanErrorMessages() {
		errorEntity = "";
		errorRol = "";
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

}
