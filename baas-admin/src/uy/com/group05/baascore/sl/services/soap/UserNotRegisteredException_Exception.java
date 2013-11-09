
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-08T22:54:30.639-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "UserNotRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class UserNotRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException userNotRegisteredException;

    public UserNotRegisteredException_Exception() {
        super();
    }
    
    public UserNotRegisteredException_Exception(String message) {
        super(message);
    }
    
    public UserNotRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException userNotRegisteredException) {
        super(message);
        this.userNotRegisteredException = userNotRegisteredException;
    }

    public UserNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException userNotRegisteredException, Throwable cause) {
        super(message, cause);
        this.userNotRegisteredException = userNotRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException getFaultInfo() {
        return this.userNotRegisteredException;
    }
}
