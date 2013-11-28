package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import uy.com.group05.baasadmin.common.exceptions.EntityPermissionException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Operacion;
import uy.com.group05.baasadmin.pl.models.Rol;
import uy.com.group05.baasadmin.pl.models.RolEntityPermission;
import uy.com.group05.baascore.sl.services.soap.PermissionEntityDTO;

@ManagedBean(name="rolBean")
@ViewScoped
public class RolBean {

	private Rol rol;

	private List<Entity> entityList;

	private List<Operacion> operationList;
	
	private List<RolEntityPermission> permissionList;

	private RolEntityPermission[][] datos;
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Operacion> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operacion> operationList) {
		this.operationList = operationList;
	}

	public List<RolEntityPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<RolEntityPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public RolEntityPermission[][] getDatos() {
		return datos;
	}

	public void setDatos(RolEntityPermission[][] datos) {
		this.datos = datos;
	}

	public Boolean[][] getDatosVista() {
		return datosVista;
	}

	public void setDatosVista(Boolean[][] datosVista) {
		this.datosVista = datosVista;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	private Boolean[][] datosVista;
	
	private long appId;	
	
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

	

	public RolBean() {

		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String paramAppId = parameterMap.get("appId");

		String paramRolId = parameterMap.get("rolId");
		if (paramAppId != null && paramRolId != null) {
			appId = Long.parseLong(paramAppId);
			long rolId = Long.parseLong(paramRolId);

			ApplicationController appController = new ApplicationController();

			entityList = appController.getEntities(appId);
			operationList = appController.getOperaciones(appId);
			rol = new Rol(appController.GetRolName(appId, rolId), rolId);
		
			permissionList = appController.getPermissionsByRol(appId, rolId);
			
			datos = new RolEntityPermission[entityList.size()][operationList.size()];
			datosVista = new Boolean[entityList.size()][operationList.size()];
			for (int i = 0; i < entityList.size(); i++) {
				for (int j = 0; j < operationList.size(); j++) {
					RolEntityPermission perm = new RolEntityPermission();
					perm.setEntityId(entityList.get(i).getId());
					perm.setRolId(rolId);
					perm.setOperationId(operationList.get(j).getId());
					perm.setPermission(false);
					datosVista[i][j] = false;
					
					for (RolEntityPermission roleEntityPermission : permissionList) {
						if (roleEntityPermission.getEntityId() == entityList.get(i).getId()  &&
							roleEntityPermission.getRolId() == rolId &&
							roleEntityPermission.getOperationId() == operationList.get(j).getId()) {
							
							perm.setPermissionId(roleEntityPermission.getPermissionId());
							
							perm.setPermission(true);
							datosVista[i][j] = true;
							break;
						} 
					}
					
					datos[i][j] = perm;
				}
			}

//			printArray();
		}

	}
	
	public String changePermissionAjax(AjaxBehaviorEvent event) {

		try{
//			printArray();
			
			int row =  (Integer)event.getComponent().getAttributes().get("row");
			int col = (Integer)event.getComponent().getAttributes().get("col");
			datos[row][col].setPermission(!datos[row][col].isPermission());
			
			cloneArray();
		
//			printArray();
		}
		catch(Exception e){
			
		}

		return null;
	}
	
	public String submit(){
		
		try{
			error = "";
			
			ApplicationController appController = new ApplicationController();
			
			
			
			List<PermissionEntityDTO> permisos = new ArrayList<PermissionEntityDTO>();
			
			for (int i = 0; i < entityList.size(); i++) {				
				for (int j = 0; j < operationList.size(); j++) {
					PermissionEntityDTO p = new PermissionEntityDTO();
					p.setHas(datos[i][j].isPermission());
					p.setIdOperation(datos[i][j].getOperationId());
					p.setIdEntity(datos[i][j].getEntityId());
					permisos.add(p);
				}
				
			}
			
			
			appController.saveRolPermissions(getUserSessionManagementBean().getUser().getUserId(), appId, 
					rol.getId(), permisos );
			
			
		
			return "/pages/App/Index.xhtml?faces-redirect=true&id=" + appId;
		
		}
		catch(EntityPermissionException e){
			error = e.getMessage();
			return "";
		}
		
	}

	public String changePermission() {
		System.out.println("imprimiendo post toda pagina");
//		printArray();

		return null;
	}

//	
//	private void printArray() {
//
//		for (int i = 0; i < roleList.size(); i++) {
//
//			System.out.print("datos Fila" + i + "[");
//			for (int j = 0; j < operationList.size(); j++) {
//				System.out.print(datos[i][j].isPermission() + ",");
//			}
//			System.out.println("]");
//		}
//		
//		for (int i = 0; i < roleList.size(); i++) {
//
//			System.out.print("datosVista Fila" + i + "[");
//			for (int j = 0; j < operationList.size(); j++) {
//				System.out.print(datosVista[i][j]+ ",");
//			}
//			System.out.println("]");
//		}
//
//	}

	
	private void cloneArray(){
		
		for (int i = 0; i < entityList.size(); i++) {
			for (int j = 0; j < operationList.size(); j++) {
				datosVista[i][j] = datos[i][j].isPermission();
			}
		}
		
	}

	
}
