package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;



@ManagedBean(name="createApplicationBean")
@SessionScoped
public class CreateApplicationBean {
	
	private String roleName;
	
	private String entityName;
	
	private String appName;
	
	private String appNameRol;
	
	private String appNameEntity;
	
	@ManagedProperty(value="#{userSessionManagementBean}")
	private UserSessionManagementBean userSessionManagementBean;
	
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
	 
	//getter and setter methods
 
	private ArrayList<String> rolList = 
		new ArrayList<String>();
	
	private ArrayList<String> entityList = 
			new ArrayList<String>();
		
 
	public ArrayList<String> getRolList() {
 
		return rolList;
 
	}
	
	public ArrayList<String> getEntityList() {
		 
		return entityList;
 
	}
 
	public String addRole() {
 
		appName = appNameRol;
		
		System.out.println("addRole: "+ appNameRol);
		
		rolList.add(roleName);
		roleName = "";
		
		return null;
	}
 
	public String deleteRole(String name) {
 
		rolList.remove(name);
		return null;
	}
	
	public String addEntity() {
		 
		
		appName = appNameEntity;
		
		entityList.add(entityName);
		entityName = "";
		
		return null;
	}
 
	public String deleteEntity(String name) {
 
		entityList.remove(name);
		return null;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String Create(){
		
		ApplicationController appController = new ApplicationController();
		
		long userId = getUserSessionManagementBean().getUser().getUserId();
		
		System.out.println("Create= userId: " +userId+ ", appName: " + appName + ", roleCount: " +rolList.size());
		
		if(appName == null)
			return "";
		
		try{
			appController.CreateApplication(userId,
					appName, rolList, entityList);
		}
		catch(Exception e){
		
			return "";
			
		}
		
		appName = "";
		rolList = new ArrayList<String>();
		entityList = new ArrayList<String>();
		
		
		return "/pages/dashboard/Index.xhtml?faces-redirect=true";
		
		//return "";
	}

	public String getAppNameRol() {
		return appNameRol;
	}

	public void setAppNameRol(String appNameRol) {
		this.appNameRol = appNameRol;
	}

	public String getAppNameEntity() {
		return appNameEntity;
	}

	public void setAppNameEntity(String appNameEntity) {
		this.appNameEntity = appNameEntity;
	}
	
}
