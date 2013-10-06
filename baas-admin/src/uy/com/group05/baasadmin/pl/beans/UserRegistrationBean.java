package uy.com.group05.baasadmin.pl.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baasadmin.common.exceptions.UnhandledRegistrationException;
import uy.com.group05.baasadmin.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baasadmin.pl.controllers.UserController;
import uy.com.group05.baasadmin.pl.models.UserModel;

@ManagedBean
@RequestScoped
public class UserRegistrationBean {
	
	
	private UIComponent email;
	
	private UserModel user = new UserModel();

	private UserController userController = new UserController();
	
	private String error;
	
	private boolean errorVisible;
	
	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;

	public UserRegistrationBean(){
		error = "";
		errorVisible = false;
	}

	public UIComponent getEmail() {
		return email;
	}

	public void setEmail(UIComponent email) {
		this.email = email;
	}

	public UserModel getUser() {
		return user;
	} 

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public UserSessionManagementBean getUserSessionManagementBean() {
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

	public String registerUser()  {
		
		errorVisible = false;
		
		try {
			long UserId = userController.registerUser(user);
			
			user.setUserId(UserId);
			
			userSessionManagementBean.setUser(user);
			
			return "/pages/dashboard/Index.xhtml?faces-redirect=true";
		}
		
		catch (Exception e) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(getEmail().getClientId(context), new FacesMessage(e.getMessage()));
//			

			error = e.getMessage(); 
			errorVisible = true;
			
			return "/pages/users/register";
		}
		
		
	}
	
	public String cancel() {
		return "/index";
	}
}