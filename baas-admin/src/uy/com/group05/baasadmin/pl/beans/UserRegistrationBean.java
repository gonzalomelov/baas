package uy.com.group05.baasadmin.pl.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import uy.com.group05.baasadmin.pl.controllers.UserController;
import uy.com.group05.baasadmin.pl.models.UserModel;

@ManagedBean
@RequestScoped
public class UserRegistrationBean {
	private UserModel user = new UserModel();

	private UserController userController = new UserController();
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public String registerUser() {
		boolean registerOk = userController.registerUser(user);
		
		if (!registerOk) {
			//TODO Cargo errores
		}
		
		return "/index";
	}
	
	public String cancel() {
		return "/index";
	}
}