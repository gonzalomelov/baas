package uy.com.group05.baasadmin.pl.beans;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import uy.com.group05.baasadmin.common.exceptions.ApplicationException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.AppModel;

@Named("dashboardBean")
@RequestScoped
public class DashboardBean {
	
	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
	private AppModel appModel;
	
	private ApplicationController appController = new ApplicationController();
	
	public UserSessionManagementBean getUserSessionManagementBean() {
		return userSessionManagementBean;
	}

	public void setUserSessionManagementBean(
			UserSessionManagementBean userSessionManagementBean) {
		this.userSessionManagementBean = userSessionManagementBean;
	}

	public DashboardBean() {
		try {
			if(userSessionManagementBean == null){
				FacesContext context = FacesContext.getCurrentInstance();
				userSessionManagementBean = context.getApplication()
						.evaluateExpressionGet(context,"#{userSessionManagementBean}", 
								UserSessionManagementBean.class);
			}
			
			long id = userSessionManagementBean.getUser().getUserId();
			appModel = appController.GetAplicaciones(id);
		} catch (ApplicationException e) {
			appModel = new AppModel();
		}
	}

	public AppModel getAppModel() {
		return appModel;
	}

	public void setAppModel(AppModel appModel) {
		this.appModel = appModel;
	}

}
