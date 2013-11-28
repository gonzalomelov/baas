
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package uy.com.group05.baascore.sl.services.soap;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T06:10:48.934-02:00
 * Generated source version: 2.4.6
 * 
 */

@javax.jws.WebService(
                      serviceName = "UserServices",
                      portName = "UserServicesPort",
                      targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/",
                      wsdlLocation = "http://baascore-group5tsi2.rhcloud.com/baas-core/UserServices?wsdl",
                      endpointInterface = "uy.com.group05.baascore.sl.services.soap.UserServices")
                      
public class UserServicesImpl implements UserServices {

    private static final Logger LOG = Logger.getLogger(UserServicesImpl.class.getName());

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#isUserLoggedIn(java.lang.String  email )*
     */
    public boolean isUserLoggedIn(java.lang.String email) throws UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation isUserLoggedIn");
        System.out.println(email);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#getUsers(*
     */
    public java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> getUsers() { 
        LOG.info("Executing operation getUsers");
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> _return = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.UserDTO>();
            uy.com.group05.baascore.sl.services.soap.UserDTO _returnVal1 = new uy.com.group05.baascore.sl.services.soap.UserDTO();
            _returnVal1.setEmail("Email-1885896508");
            _returnVal1.setId(-1416549045771727124l);
            _returnVal1.setLastname("Lastname-2009904162");
            _returnVal1.setLoggedIn(true);
            _returnVal1.setName("Name1926800523");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#registerUser(uy.com.group05.baascore.sl.services.soap.UserRegisterDTO  user )*
     */
    public uy.com.group05.baascore.sl.services.soap.UserDTO registerUser(uy.com.group05.baascore.sl.services.soap.UserRegisterDTO user) throws EmailAlreadyRegisteredException_Exception    { 
        LOG.info("Executing operation registerUser");
        System.out.println(user);
        try {
            uy.com.group05.baascore.sl.services.soap.UserDTO _return = new uy.com.group05.baascore.sl.services.soap.UserDTO();
            _return.setEmail("Email1363496914");
            _return.setId(6691450065779709583l);
            _return.setLastname("Lastname360829045");
            _return.setLoggedIn(false);
            _return.setName("Name-1132202084");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new EmailAlreadyRegisteredException_Exception("EmailAlreadyRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#logoutUser(java.lang.String  email )*
     */
    public boolean logoutUser(java.lang.String email) throws UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation logoutUser");
        System.out.println(email);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#loginUserFacebook(java.lang.String  email ,)java.lang.String  name ,)java.lang.String  lastname ,)java.lang.String  fbId )*
     */
    public uy.com.group05.baascore.sl.services.soap.UserDTO loginUserFacebook(java.lang.String email,java.lang.String name,java.lang.String lastname,java.lang.String fbId) { 
        LOG.info("Executing operation loginUserFacebook");
        System.out.println(email);
        System.out.println(name);
        System.out.println(lastname);
        System.out.println(fbId);
        try {
            uy.com.group05.baascore.sl.services.soap.UserDTO _return = new uy.com.group05.baascore.sl.services.soap.UserDTO();
            _return.setEmail("Email-794335617");
            _return.setId(3392883848239162325l);
            _return.setLastname("Lastname1457568384");
            _return.setLoggedIn(true);
            _return.setName("Name-688045186");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#validateUser(java.lang.String  email ,)java.lang.String  password )*
     */
    public boolean validateUser(java.lang.String email,java.lang.String password) throws UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation validateUser");
        System.out.println(email);
        System.out.println(password);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.UserServices#loginUser(java.lang.String  email ,)java.lang.String  password )*
     */
    public uy.com.group05.baascore.sl.services.soap.UserDTO loginUser(java.lang.String email,java.lang.String password) throws UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation loginUser");
        System.out.println(email);
        System.out.println(password);
        try {
            uy.com.group05.baascore.sl.services.soap.UserDTO _return = new uy.com.group05.baascore.sl.services.soap.UserDTO();
            _return.setEmail("Email159579318");
            _return.setId(4266383458402021650l);
            _return.setLastname("Lastname1806948974");
            _return.setLoggedIn(false);
            _return.setName("Name1112982236");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

}
