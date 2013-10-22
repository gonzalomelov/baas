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
import uy.com.group05.baascore.sl.services.soap.PermissionRoleDTO;

@ManagedBean(name="entityBean")
@ViewScoped
public class EntityBean {

	private Entity entity;

	private List<Rol> roleList;

	private List<Operacion> operationList;
	
	private List<RolEntityPermission> permissionList;

	private RolEntityPermission[][] datos;
	
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

	public List<Rol> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Rol> roleList) {
		this.roleList = roleList;
	}

	public List<Operacion> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operacion> operationList) {
		this.operationList = operationList;
	}

	public EntityBean() {

		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String paramAppId = parameterMap.get("appId");

		String paramEntityId = parameterMap.get("entityId");
		if (paramAppId != null && paramEntityId != null) {
			appId = Long.parseLong(paramAppId);
			long entityId = Long.parseLong(paramEntityId);

			ApplicationController appController = new ApplicationController();

			roleList = appController.getRoles(appId);
			operationList = appController.getOperaciones(appId);
			entity = appController.getEntity(entityId);
			permissionList = appController.getPermissions(appId, entityId);
			
			datos = new RolEntityPermission[roleList.size()][operationList.size()];
			datosVista = new Boolean[roleList.size()][operationList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				for (int j = 0; j < operationList.size(); j++) {
					RolEntityPermission perm = new RolEntityPermission();
					perm.setEntityId(entityId);
					perm.setRolId(roleList.get(i).getId());
					perm.setOperationId(operationList.get(j).getId());
					perm.setPermission(false);
					datosVista[i][j] = false;
					
					for (RolEntityPermission roleEntityPermission : permissionList) {
						if (roleEntityPermission.getEntityId() == entityId &&
							roleEntityPermission.getRolId() == roleList.get(i).getId() &&
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

			printArray();
		}

	}
	
	public String changePermissionAjax(AjaxBehaviorEvent event) {

		try{
//			printArray();
			
			int row =  (Integer)event.getComponent().getAttributes().get("row");
			int col = (Integer)event.getComponent().getAttributes().get("col");
			datos[row][col].setPermission(!datos[row][col].isPermission());
			
			cloneArray();
		
			printArray();
		}
		catch(Exception e){
			
		}

		return null;
	}
	
	public String submit(){
		
		try{
			error = "";
			
			ApplicationController appController = new ApplicationController();
			
			
			
			List<PermissionRoleDTO> permisos = new ArrayList<PermissionRoleDTO>();
			
			for (int i = 0; i < roleList.size(); i++) {				
				for (int j = 0; j < operationList.size(); j++) {
					PermissionRoleDTO p = new PermissionRoleDTO();
					p.setHas(datos[i][j].isPermission());
					p.setIdOperation(datos[i][j].getOperationId());
					p.setIdRole(datos[i][j].getRolId());
					permisos.add(p);
				}
				
			}
			
			
			appController.saveEntityPermissions(userSessionManagementBean.getUser().getUserId(), appId, 
					entity.getId(), permisos );
			
		
			return "/pages/App/Index.xhtml?faces-redirect=true&id=" + appId;
		
		}
		catch(EntityPermissionException e){
			error = e.getMessage();
			return "";
		}
		
	}

	public String changePermission() {
		System.out.println("imprimiendo post toda pagina");
		printArray();

		return null;
	}

	public RolEntityPermission[][] getDatos() {
		return datos;
	}

	public void setDatos(RolEntityPermission[][] datos) {
		this.datos = datos;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	private void printArray() {

		for (int i = 0; i < roleList.size(); i++) {

			System.out.print("datos Fila" + i + "[");
			for (int j = 0; j < operationList.size(); j++) {
				System.out.print(datos[i][j].isPermission() + ",");
			}
			System.out.println("]");
		}
		
		for (int i = 0; i < roleList.size(); i++) {

			System.out.print("datosVista Fila" + i + "[");
			for (int j = 0; j < operationList.size(); j++) {
				System.out.print(datosVista[i][j]+ ",");
			}
			System.out.println("]");
		}

	}

	public Boolean[][] getDatosVista() {
		return datosVista;
	}

	public void setDatosVista(Boolean[][] datosVista) {
		this.datosVista = datosVista;
	}
	
	private void cloneArray(){
		
		for (int i = 0; i < roleList.size(); i++) {
			for (int j = 0; j < operationList.size(); j++) {
				datosVista[i][j] = datos[i][j].isPermission();
			}
		}
		
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
