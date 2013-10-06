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
			
					
			user.setEmail(userModel.getEmail());
			user.setLastname(userModel.getLastname());
			user.setName(userModel.getName());
			
			
			
			userSessionManagementBean.setUser(user);	
			
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
}