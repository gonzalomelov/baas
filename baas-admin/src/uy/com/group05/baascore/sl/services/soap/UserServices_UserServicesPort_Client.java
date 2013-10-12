
package uy.com.group05.baascore.sl.services.soap;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import uy.com.group05.baascore.sl.services.impl.UserServices;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-06T16:19:20.001-02:00
 * Generated source version: 2.4.6
 * 
 */
public final class UserServices_UserServicesPort_Client {

    private static final QName SERVICE_NAME = new QName("http://impl.services.sl.baascore.group05.com.uy/", "UserServices");

    private UserServices_UserServicesPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = UserServices.WSDL_LOCATION;
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
      
        UserServices ss = new UserServices(wsdlURL, SERVICE_NAME);
        UserServices port = (UserServices) ss.getUserServicesPort();  
        
        {
        System.out.println("Invoking isUserLoggedIn...");
        java.lang.String _isUserLoggedIn_email = "_isUserLoggedIn_email231888575";
        try {
            boolean _isUserLoggedIn__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).isUserLoggedIn(_isUserLoggedIn_email);
            System.out.println("isUserLoggedIn.result=" + _isUserLoggedIn__return);

        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking getUsers...");
        java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> _getUsers__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).getUsers();
        System.out.println("getUsers.result=" + _getUsers__return);


        }
        {
        System.out.println("Invoking registerUser...");
        uy.com.group05.baascore.sl.services.soap.UserRegisterDTO _registerUser_user = new uy.com.group05.baascore.sl.services.soap.UserRegisterDTO();
        _registerUser_user.setEmail("Email-381199696");
        _registerUser_user.setLastname("Lastname804477608");
        _registerUser_user.setName("Name-1299534850");
        _registerUser_user.setPassword("Password1263669186");
        try {
            uy.com.group05.baascore.sl.services.soap.UserDTO _registerUser__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).registerUser(_registerUser_user);
            System.out.println("registerUser.result=" + _registerUser__return);

        } catch (EmailAlreadyRegisteredException_Exception e) { 
            System.out.println("Expected exception: EmailAlreadyRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking logoutUser...");
        java.lang.String _logoutUser_email = "_logoutUser_email1089921052";
        try {
            boolean _logoutUser__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).logoutUser(_logoutUser_email);
            System.out.println("logoutUser.result=" + _logoutUser__return);

        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking validateUser...");
        java.lang.String _validateUser_email = "_validateUser_email-494726503";
        java.lang.String _validateUser_password = "_validateUser_password-1406737200";
        try {
            boolean _validateUser__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).validateUser(_validateUser_email, _validateUser_password);
            System.out.println("validateUser.result=" + _validateUser__return);

        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking loginUser...");
        java.lang.String _loginUser_email = "_loginUser_email-2028511400";
        java.lang.String _loginUser_password = "_loginUser_password-925714961";
        try {
            uy.com.group05.baascore.sl.services.soap.UserDTO _loginUser__return = ((uy.com.group05.baascore.sl.services.soap.UserServices) port).loginUser(_loginUser_email, _loginUser_password);
            System.out.println("loginUser.result=" + _loginUser__return);

        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
