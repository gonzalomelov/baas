package uy.com.group05.baasadmin.pl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.jboss.security.auth.spi.Users;

import uy.com.group05.baasadmin.common.exceptions.ClientRolException;
import uy.com.group05.baasadmin.common.exceptions.EntityPermissionException;
import uy.com.group05.baasadmin.pl.controllers.ApplicationController;
import uy.com.group05.baasadmin.pl.controllers.ClientController;
import uy.com.group05.baasadmin.pl.models.Cliente;
import uy.com.group05.baasadmin.pl.models.Rol;
import uy.com.group05.baasadmin.pl.models.RolEntityPermission;
import uy.com.group05.baasadmin.pl.models.RoleCliente;
import uy.com.group05.baascore.sl.services.soap.PermissionRoleDTO;
import uy.com.group05.baascore.sl.services.soap.RoleDTO;
import uy.com.group05.baascore.sl.services.soap.RolesClientDTO;

@ManagedBean(name = "clientRoleBean")
@ViewScoped
public class ClientRoleBean {

	private Cliente cliente;

	private List<Rol> roleList;

	private RoleCliente[] datos;

	private Boolean[] datosVista;
	
	private List<String> permisos;

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

	public ClientRoleBean() {

		Map<String, String> parameterMap = (Map<String, String>) FacesContext
				.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String paramAppId = parameterMap.get("appId");

		String paramClientId = parameterMap.get("clientId");
		error = "";
		if (paramAppId != null && paramClientId != null) {
			
			try{
			permisos = new ArrayList<String>();
			
			permisos.add("");
			
			appId = Long.parseLong(paramAppId);
			long clientId = Long.parseLong(paramClientId);

			ApplicationController appController = new ApplicationController();

			roleList = appController.getRoles(appId);
			// operationList = appController.getOperaciones(appId);
			cliente = appController.getCliente(clientId);
			
			ClientController clientController = new ClientController();
			
			List<RoleDTO> rolesCliente =  clientController.GetClientRoles(appId, 
					getUserSessionManagementBean().getUser().getUserId(), clientId);
			
			
			datos = new RoleCliente[roleList.size()];
			datosVista = new Boolean[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {

				RoleCliente rolCliente = new RoleCliente();
				rolCliente.setClientId(clientId);
				rolCliente.setRoleId(roleList.get(i).getId());
				
				
				rolCliente.setRol(false);
				datosVista[i] = false;
				
				for (RoleDTO rol : rolesCliente) {
					if (rol.getId() == rolCliente.getRoleId()) {
						
						rolCliente.setRol(true);
						datosVista[i] = true;
						break;
					} 
				}

				datos[i] = rolCliente;
			}
			}
			catch(Exception e){
				error = e.getMessage();
				return;
			}

			
		}
		
		

	}

	public String changePermissionAjax(AjaxBehaviorEvent event) {

		try {
			// printArray();

			int row = (Integer) event.getComponent().getAttributes().get("row");

			datos[row].setRol(!datos[row].isRol());

			cloneArray();

			printArray();
		} catch (Exception e) {

		}

		return null;
	}

	public String submit() {

		
		printArray();
		try{
			error = "";
			
			ClientController clientController = new ClientController();
			
			List<RolesClientDTO> permisos = new ArrayList<RolesClientDTO>();
			
			for (int i = 0; i < roleList.size(); i++) {	
				RolesClientDTO r = new RolesClientDTO();
				r.setIdRole(datos[i].getRoleId());
				r.setHas(datos[i].isRol());
				permisos.add(r);
				
				
			}
			
			
			clientController.saveClientRoles(appId, getUserSessionManagementBean().getUser().getUserId(), 
					cliente.getId(), permisos);
			
		
			return "/pages/App/Index.xhtml?faces-redirect=true&id=" + appId;
		
		
		} catch (ClientRolException e) {
			error = e.getMessage();
			return "";
		}
		
		
	}

	public String changePermission() {
		System.out.println("imprimiendo post toda pagina");
		printArray();

		return null;
	}

	private void printArray() {
		System.out.print("datos Fila [");
		for (int i = 0; i < roleList.size(); i++) {

			System.out.print(datos[i].isRol() + ",");
		}
		System.out.println("]");

		System.out.print("datosVista Fila [");
		for (int i = 0; i < roleList.size(); i++) {

			System.out.print(datosVista[i] + ",");
		}
		System.out.println("]");

	}

		private void cloneArray() {

		for (int i = 0; i < roleList.size(); i++) {
			
				datosVista[i] = datos[i].isRol();
			
		}

	}

	public RoleCliente[] getDatos() {
			return datos;
		}

		public void setDatos(RoleCliente[] datos) {
			this.datos = datos;
		}

		public Boolean[] getDatosVista() {
			return datosVista;
		}

		public void setDatosVista(Boolean[] datosVista) {
			this.datosVista = datosVista;
		}

		public long getAppId() {
			return appId;
		}

		public void setAppId(long appId) {
			this.appId = appId;
		}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
