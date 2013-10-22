
package uy.com.group05.baascore.sl.services.soap;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import uy.com.group05.baascore.sl.services.impl.ClientServices;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-21T23:57:37.307-02:00
 * Generated source version: 2.4.6
 * 
 */
public final class ClientServices_ClientServicesPort_Client {

    private static final QName SERVICE_NAME = new QName("http://impl.services.sl.baascore.group05.com.uy/", "ClientServices");

    private ClientServices_ClientServicesPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ClientServices.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ClientServices ss = new ClientServices(wsdlURL, SERVICE_NAME);
        ClientServices port = ss.getClientServicesPort();  
        
        {
        System.out.println("Invoking getClient...");
        long _getClient_appId = 4047828367901453202l;
        long _getClient_clientId = -9121419788424213365l;
        uy.com.group05.baascore.sl.services.soap.ClientDTO _getClient__return = port.getClient(_getClient_appId, _getClient_clientId);
        System.out.println("getClient.result=" + _getClient__return);


        }
        {
        System.out.println("Invoking assignRoleToClients...");
        long _assignRoleToClients_idApp = -6620676650717823603l;
        long _assignRoleToClients_idUser = 3243395044042759051l;
        long _assignRoleToClients_idClient = -4752499194166743357l;
        java.util.List<uy.com.group05.baascore.sl.services.soap.RolesClientDTO> _assignRoleToClients_rolesClient = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.RolesClientDTO>();
        uy.com.group05.baascore.sl.services.soap.RolesClientDTO _assignRoleToClients_rolesClientVal1 = new uy.com.group05.baascore.sl.services.soap.RolesClientDTO();
        _assignRoleToClients_rolesClientVal1.setHas(false);
        _assignRoleToClients_rolesClientVal1.setIdRole(6122692915715018229l);
        _assignRoleToClients_rolesClient.add(_assignRoleToClients_rolesClientVal1);
        try {
            boolean _assignRoleToClients__return = port.assignRoleToClients(_assignRoleToClients_idApp, _assignRoleToClients_idUser, _assignRoleToClients_idClient, _assignRoleToClients_rolesClient);
            System.out.println("assignRoleToClients.result=" + _assignRoleToClients__return);

        } catch (ClientNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: ClientNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserCantAccessAppException_Exception e) { 
            System.out.println("Expected exception: UserCantAccessAppException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getRolesFromClient...");
        long _getRolesFromClient_idApp = 4467036269503830096l;
        long _getRolesFromClient_idUser = 1800172947389338684l;
        long _getRolesFromClient_idClient = -4403393661260204807l;
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.RoleDTO> _getRolesFromClient__return = port.getRolesFromClient(_getRolesFromClient_idApp, _getRolesFromClient_idUser, _getRolesFromClient_idClient);
            System.out.println("getRolesFromClient.result=" + _getRolesFromClient__return);

        } catch (ClientNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: ClientNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserCantAccessAppException_Exception e) { 
            System.out.println("Expected exception: UserCantAccessAppException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking assignRoleToClient...");
        long _assignRoleToClient_idApp = -2260042987629067979l;
        long _assignRoleToClient_idUser = -7731092304322599600l;
        long _assignRoleToClient_idRole = -2408499777711012815l;
        long _assignRoleToClient_idClient = 8342790793194254837l;
        try {
            boolean _assignRoleToClient__return = port.assignRoleToClient(_assignRoleToClient_idApp, _assignRoleToClient_idUser, _assignRoleToClient_idRole, _assignRoleToClient_idClient);
            System.out.println("assignRoleToClient.result=" + _assignRoleToClient__return);

        } catch (ClientNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: ClientNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (EntityNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: EntityNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserCantAccessAppException_Exception e) { 
            System.out.println("Expected exception: UserCantAccessAppException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}