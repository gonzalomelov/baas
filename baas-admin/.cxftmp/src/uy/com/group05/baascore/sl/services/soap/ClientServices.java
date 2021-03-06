package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T06:11:35.467-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "ClientServices")
@XmlSeeAlso({ObjectFactory.class})
public interface ClientServices {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getClient", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetClient")
    @WebMethod
    @ResponseWrapper(localName = "getClientResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetClientResponse")
    public uy.com.group05.baascore.sl.services.soap.ClientDTO getClient(
        @WebParam(name = "appId", targetNamespace = "")
        long appId,
        @WebParam(name = "clientId", targetNamespace = "")
        long clientId
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assignRoleToClients", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignRoleToClients")
    @WebMethod
    @ResponseWrapper(localName = "assignRoleToClientsResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignRoleToClientsResponse")
    public boolean assignRoleToClients(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idUser", targetNamespace = "")
        long idUser,
        @WebParam(name = "idClient", targetNamespace = "")
        long idClient,
        @WebParam(name = "rolesClient", targetNamespace = "")
        java.util.List<uy.com.group05.baascore.sl.services.soap.RolesClientDTO> rolesClient
    ) throws ClientNotRegisteredException_Exception, UserCantAccessAppException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getRolesFromClient", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetRolesFromClient")
    @WebMethod
    @ResponseWrapper(localName = "getRolesFromClientResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetRolesFromClientResponse")
    public java.util.List<uy.com.group05.baascore.sl.services.soap.RoleDTO> getRolesFromClient(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idUser", targetNamespace = "")
        long idUser,
        @WebParam(name = "idClient", targetNamespace = "")
        long idClient
    ) throws ClientNotRegisteredException_Exception, UserCantAccessAppException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assignRoleToClient", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignRoleToClient")
    @WebMethod
    @ResponseWrapper(localName = "assignRoleToClientResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignRoleToClientResponse")
    public boolean assignRoleToClient(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idUser", targetNamespace = "")
        long idUser,
        @WebParam(name = "idRole", targetNamespace = "")
        long idRole,
        @WebParam(name = "idClient", targetNamespace = "")
        long idClient
    ) throws ClientNotRegisteredException_Exception, EntityNotRegisteredException_Exception, UserCantAccessAppException_Exception, AppNotRegisteredException_Exception;
}
