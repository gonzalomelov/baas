package uy.com.group05.baasadmin.pl.beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Operacion;
import uy.com.group05.baasadmin.pl.models.Rol;
import uy.com.group05.baasadmin.pl.models.RolEntityPermission;

@ManagedBean(name="entityBean")
@ViewScoped
public class EntityBean {

	private Entity entity;

	private List<Rol> roleList;

	private List<Operacion> operationList;

	private RolEntityPermission[][] datos;
	
	private Boolean[][] datosVista;
	
	private long appId;

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

			datos = new RolEntityPermission[roleList.size()][operationList.size()];
			datosVista = new Boolean[roleList.size()][operationList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				for (int j = 0; j < operationList.size(); j++) {
					RolEntityPermission perm = new RolEntityPermission();
					perm.setEntityId(entityId);
					perm.setPermissionId(operationList.get(j).getId());
					perm.setRolId(roleList.get(i).getId());
					if ((j % 2) == 0) {
						perm.setPermission(true);
						datosVista[i][j] = true;
					} else {
						perm.setPermission(false);
						datosVista[i][j] = false;
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
		
		printArray();
		
		return "/pages/App/Index.xhtml?faces-redirect=true&id=" + appId;
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
}
