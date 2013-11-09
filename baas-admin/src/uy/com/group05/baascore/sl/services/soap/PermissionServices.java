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
 * 2013-11-09T00:07:39.396-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "PermissionServices")
@XmlSeeAlso({ObjectFactory.class})
public interface PermissionServices {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assingPermissionRole", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssingPermissionRole")
    @WebMethod
    @ResponseWrapper(localName = "assingPermissionRoleResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssingPermissionRoleResponse")
    public boolean assingPermissionRole(
        @WebParam(name = "idUser", targetNamespace = "")
        long idUser,
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idRole", targetNamespace = "")
        long idRole,
        @WebParam(name = "permEntities", targetNamespace = "")
        java.util.List<uy.com.group05.baascore.sl.services.soap.PermissionEntityDTO> permEntities
    ) throws RoleNotRegisteredException_Exception, UserCantAccessAppException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assingPermissionEntity", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssingPermissionEntity")
    @WebMethod
    @ResponseWrapper(localName = "assingPermissionEntityResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssingPermissionEntityResponse")
    public boolean assingPermissionEntity(
        @WebParam(name = "idUser", targetNamespace = "")
        long idUser,
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idEntity", targetNamespace = "")
        long idEntity,
        @WebParam(name = "permRoles", targetNamespace = "")
        java.util.List<uy.com.group05.baascore.sl.services.soap.PermissionRoleDTO> permRoles
    ) throws EntityNotRegisteredException_Exception, UserCantAccessAppException_Exception, AppNotRegisteredException_Exception;
}
