package uy.com.group05.baasadmin.pl.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import uy.com.group05.baasadmin.pl.controllers.UserController;
import uy.com.group05.baasadmin.pl.models.UserModel;

@ManagedBean
@RequestScoped
public class UserLoginBean {
	private UserModel user = new UserModel();

	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
	private UserController userController = new UserController();
	
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

	//Functions
	
	public String login() {
		UserModel userModel = userController.loginUser(user.getUsername(), user.getPassword());
		
		if (userModel == null) {
			return "/index";
		}
		
		user.setEmail(userModel.getEmail());
		user.setLastname(userModel.getLastname());
		user.setName(userModel.getName());
		user.setUsername(userModel.getUsername());
		
		userSessionManagementBean.setUser(user);	
		
		return "/index";
	}
	
	public String logout() {
		boolean logoutOk = userController.logoutUser(userSessionManagementBean.getUser().getUsername());
		
		if (!logoutOk) {
			return "/profile";
		}
		
		userSessionManagementBean.setUser(null);
		
		return "/index";
	}
}