package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;



@ManagedBean(name="createApplicationBean")
@ViewScoped
public class CreateApplicationBean {
	
	private String roleName;
	
	private String entityName;
	
	private String appName;
	
	private String appNameRol;
	
	private String appNameEntity;
	
	private String errorRol;
	
	private String errorEntity;
	
	private String error;
	
		
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
 
		CleanErrorMessages();
		
		appName = appNameRol;
		
		
		if(ExisteEnLista(roleName, rolList)){
			errorRol = "Ya existe el rol:"+ roleName;
			roleName = "";
			return null;
		}
		
		
		rolList.add(roleName);
		roleName = "";
		
		return null;
	}
 
	public String deleteRole(String name) {
 
		rolList.remove(name);
		return null;
	}
	
	public String addEntity() {
		 
		CleanErrorMessages();
		appName = appNameEntity;
		
		if(ExisteEnLista(entityName, entityList)){
			errorEntity = "Ya existe la entidad:"+ entityName;
			entityName = "";
			return null;
		}
		
		
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
		
		CleanErrorMessages();
		
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
		
			error = e.getMessage();
			
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

	public String getErrorRol() {
		return errorRol;
	}

	public void setErrorRol(String errorRol) {
		this.errorRol = errorRol;
	}
	
	private boolean ExisteEnLista(String element, List<String> list){
		
		boolean retorno = false;
		
		for (String elem : list) {
			if(elem.equals(element)){
				return true;
			}
		}
		
		return retorno;
	}

	public String getErrorEntity() {
		return errorEntity;
	}

	public void setErrorEntity(String errorEntity) {
		this.errorEntity = errorEntity;
	}
	
	
	private void CleanErrorMessages(){
		errorEntity = "";
		errorRol = "" ;
		error = "";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
