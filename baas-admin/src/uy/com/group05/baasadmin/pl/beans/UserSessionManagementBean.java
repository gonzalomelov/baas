package uy.com.group05.baasadmin.pl.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uy.com.group05.baasadmin.pl.models.UserModel;

@ManagedBean(name="userSessionManagementBean")
@SessionScoped
public class UserSessionManagementBean {
	private UserModel user = null;
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public boolean isUserLoggedIn()
	{
		return user != null;
	}
	
	
}