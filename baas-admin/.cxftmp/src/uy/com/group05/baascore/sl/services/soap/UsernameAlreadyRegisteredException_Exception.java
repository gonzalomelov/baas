
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-09-28T09:31:11.584-05:00
 * Generated source version: 2.7.6
 */

@WebFault(name = "UsernameAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class UsernameAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.UsernameAlreadyRegisteredException usernameAlreadyRegisteredException;

    public UsernameAlreadyRegisteredException_Exception() {
        super();
    }
    
    public UsernameAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public UsernameAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.UsernameAlreadyRegisteredException usernameAlreadyRegisteredException) {
        super(message);
        this.usernameAlreadyRegisteredException = usernameAlreadyRegisteredException;
    }

    public UsernameAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.UsernameAlreadyRegisteredException usernameAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.usernameAlreadyRegisteredException = usernameAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.UsernameAlreadyRegisteredException getFaultInfo() {
        return this.usernameAlreadyRegisteredException;
    }
}