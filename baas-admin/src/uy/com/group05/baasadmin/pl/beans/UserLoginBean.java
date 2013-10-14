package uy.com.group05.baasadmin.pl.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.pl.controllers.UserController;
import uy.com.group05.baasadmin.pl.models.UserModel;

@ManagedBean
@RequestScoped
public class UserLoginBean {
	private UserModel user = new UserModel();

	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
	private UserController userController = new UserController();
	
	private String error;
	
	private boolean errorVisible;
	
	public UserLoginBean(){
		error = "";
		setErrorVisible(false);
	}
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public UserSessionManagementBean getUserSessionManagementBean() {
		if(userSessionManagementBean == null){
			FacesContext context = FacesContext.getCurrentInstance();
			userSessionManagementBean = context.getApplication()
					.evaluateExpressionGet(context,"#{userSessionManagementBean}", 
							UserSessionManagementBean.class);
			
		}
		
		return userSessionManagementBean;
	}

	public void setUserSessionManagementBean(
			UserSessionManagementBean userSessionManagementBean) {
		this.userSessionManagementBean = userSessionManagementBean;
	}
	
	public String getError() {
		return error;
	}
	
	public boolean getErrorVisible(){
		
		return error != "";
	}


	public void setError(String error) {
		this.error = error;
	}

		
	//Functions

	public String login() {
		try{
		
			UserModel userModel = userController.loginUser(user.getEmail(), user.getPassword());
			
			userSessionManagementBean.setUser(userModel);	
			
			return "/pages/dashboard/Index.xhtml?faces-redirect=true";
			
		}
		catch(Exception e) {

			error = e.getMessage(); 
			setErrorVisible(true);
			
			return "/pages/users/login";
		}
		
	}

	public boolean isErrorVisible() {
		return errorVisible;
	}

	public void setErrorVisible(boolean errorVisible) {
		this.errorVisible = errorVisible;
	}
	
	public String logout() {
		//boolean logoutOk = userController.logoutUser(getUserSessionManagementBean().getUser().getEmail());
		
//		if (!logoutOk) {
//			return "";
//		}
		
		userSessionManagementBean.setUser(null);
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String getFacebookUrlAuth() {
        
        String appId = "641457435904811";
        String redirectUrl = "http://localhost:8080/baas-admin/index.sec";
        String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
                + appId + "&redirect_uri=" + redirectUrl
                + "&scope=email";
        return returnValue;
    }
}