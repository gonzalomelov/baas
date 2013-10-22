package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;
import java.util.UUID;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;
import uy.com.group05.baascore.sl.entitiesws.RolesClientDTO;

@Local
public interface ClientManagementLocal {
	
	public Client getClient(long appId, long clientId);
	
	/**
	 * Registrar un cliente a una aplicación
	 * @param apiClientId Validar aplicación que realiza la llamada
	 * @param client Cliente a agregar. Se tiene el appId en el mismo
	 * @return Resultado del registro
	 */
	public ClientRegistrationDTO register(UUID apiClientId, ClientDTO client);

	/**
	 * Autenticar y retornar un access token para un cliente de nuestro BaaS para una aplicación de nuestro BaaS
	 * @param appName Aplicación
	 * @param apiClientId Client Id de la aplicación
	 * @param email Email del cliente
	 * @param password Password del cliente
	 * @return Resultado de la autenticacion. Si es valida se retorna access token y refresh token para utilizar la API
	 */
	public ClientAuthenticationDTO authenticate(String appName, UUID apiClientId, String email, String password);
	
	/**
	 * Validacion de una llamada a la API de parte de una aplicación nuestra y de un cliente nuestro
	 * @param appName Aplicación
	 * @param operation Operacion (GET, PUT, POST, DELETE)
	 * @param entityName Entidad
	 * @param accessToken Token de acceso
	 * @return True si para dicho Token el usuario tiene permisos para la operacion sobre la entidad
	 */
	public boolean validate(String appName, String operation, String entityName, UUID accessToken);
	
	/**
	 * Autenticar y retornar un access token para un cliente de otro BaaS para una aplicación de nuestro BaaS
	 * @param appId Aplicación
	 * @param externalAppId Aplicación externa
	 * @param apiClientId Access Token de nuestra aplicación
	 * @param email Email del usuario
	 * @param password Password del usuario
	 * @return Access Token si la autenticación fue válida para realizar llamadas a nuestra API
	 */
	public ClientAuthenticationDTO authenticateExternal(long appId, long externalAppId, UUID apiClientId,
			String email, String password);
	
	/**
	 * Validacion de una llamada a la API de parte de una aplicación nuestra y de un cliente externo
	 * @param appName Aplicación nuestra
	 * @param operation GET, POST, PUT, DELETE
	 * @param entityName Entidad
	 * @param accessToken Token de acceso
	 * @return True si para dicho token la aplicación tiene permisos en su rol para clientes externos para realizar la operacion sobre la entidad
	 */
	public boolean validateExternal(String appName, String operation, String entityName, UUID accessToken);
	
	public Client getClient(long idClient);
	
	public boolean assignRoleToClient(long idApp, long idUser, long idRole, long idClient) 
			throws ClientNotRegisteredException, EntityNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException;
	
	public List<Role> getRolesFromClient(long idApp, long idUser, long idClient) throws ClientNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException;
	
	public boolean assignRoleToClients(long idApp, long idUser, long idClient, List<RolesClientDTO> rolesClient) 
			throws AppNotRegisteredException, UserCantAccessAppException, ClientNotRegisteredException;
}
