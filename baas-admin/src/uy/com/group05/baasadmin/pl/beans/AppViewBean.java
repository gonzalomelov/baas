package uy.com.group05.baasadmin.pl.beans;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Application;

@Named("appViewBean")
@RequestScoped
public class AppViewBean {

	
	
	private Application app;

	public AppViewBean() {

		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String paramId = parameterMap.get("id");

		long id = Long.parseLong(paramId);

		ApplicationController appController = new ApplicationController();

		setApp(appController.GetAplication(id));

		

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

	
}
