package uy.com.group05.baasadmin.pl.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
	
	private UIComponent username;
	private UIComponent email;
	
	private UserModel user = new UserModel();

	private UserController userController = new UserController();

	public UIComponent getUsername() {
		return username;
	}

	public void setUsername(UIComponent username) {
		this.username = username;
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

	public String registerUser() {
		
		try {
			userController.registerUser(user);
			
			return "/index";
		}
		catch (UsernameAlreadyRegisteredException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(getUsername().getClientId(context), new FacesMessage(e.getMessage()));
			
			return "/pages/users/register";
		}
		catch (EmailAlreadyRegisteredException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(getEmail().getClientId(context), new FacesMessage(e.getMessage()));
			
			return "/pages/users/register";
		}
		catch (UnhandledRegistrationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("", new FacesMessage(e.getMessage()));
			
			return "/pages/users/register";
		}
		
	}
	
	public String cancel() {
		return "/index";
	}
}