package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.FacesContext;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.EntitySync;
//import uy.com.group05.baascore.common.exceptions.InvalidNameException;



@ManagedBean(name="createApplicationBean")
@ViewScoped
public class CreateApplicationBean {
	
	private String roleName;
	
	private String entityName;
	
	private boolean entitySync;
	
	private String appName;	
	
	private String errorRol;
	
	private String errorEntity;
	
	private String error;
	
	@PostConstruct
	public void init() {
		rolList.add("default_local_clients");
		rolList.add("default_external_clients");
	}
	
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
	
	private ArrayList<EntitySync> entityList = 
			new ArrayList<EntitySync>();
		
 
	public ArrayList<String> getRolList() {
 
		return rolList;
 
	}
	
	public ArrayList<EntitySync> getEntityList() {
		 
		return entityList;
 
	}
 
	public String addRole() {
 
		CleanErrorMessages();
		
		if(ExisteEnListaString(roleName, rolList)){
			errorRol = "Ya existe el rol:"+ roleName;
			roleName = "";
			return null;
		}
		if(!validarNombre(roleName)){
			errorRol= "El nombre debe tener entre 5 y 30 caracteres alfanumericos.";
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
		
		if(ExisteEnLista(entityName, entityList)){
			errorEntity = "Ya existe la entidad:"+ entityName;
			entityName = "";
			entitySync = false;
			return null;
		}
		if(!validarNombre(entityName)){
			errorEntity= "El nombre debe tener entre 5 y 30 caracteres alfanumericos.";
			entityName = "";
			entitySync = false;
			return null;
		}
		
		
		entityList.add(new EntitySync(entityName, entitySync));
		entityName = "";
		entitySync = false;
		
		return null;
	}
 
	public String deleteEntity(String name) {
 
		entityList.remove(name);
		return null;
	}

	public void checkBoxListener(AjaxBehaviorEvent event) {
	    entitySync = !entitySync;
	}
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public boolean isEntitySync() {
		return entitySync;
	}

	public void setEntitySync(boolean entitySync) {
		this.entitySync = entitySync;
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
		
		if(!validarNombre(appName)){
			error= "El nombre de la aplicación debe tener entre 5 y 30 caracteres alfanumericos.";
			return "";
		}
		
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
		entityList = new ArrayList<EntitySync>();
		
		
		return "/pages/dashboard/Index.xhtml?faces-redirect=true";
		
		//return "";
	}	

	public String getErrorRol() {
		return errorRol;
	}

	public void setErrorRol(String errorRol) {
		this.errorRol = errorRol;
	}
	
	private boolean ExisteEnLista(String element, List<EntitySync> list){
		
		boolean retorno = false;
		
		for (EntitySync elem : list) {
			if(elem.getName().equals(element)){
				return true;
			}
		}
		
		return retorno;
	}
	
	private boolean ExisteEnListaString(String element, List<String> list){
		
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
	
	//++++++
	public boolean validarNombre(String nom){
		Pattern pat = Pattern.compile("[a-zA-Z0-9]{3,30}");
	     Matcher mat = pat.matcher(nom);
	     if (mat.matches()) {
	         return true;//System.out.println("SI");
	     } else {
	         return false;//System.out.println("NO");
	     }
	}
}
