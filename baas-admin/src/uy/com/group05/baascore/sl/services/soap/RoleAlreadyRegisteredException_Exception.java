
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-21T19:59:15.522-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "RoleAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class RoleAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.RoleAlreadyRegisteredException roleAlreadyRegisteredException;

    public RoleAlreadyRegisteredException_Exception() {
        super();
    }
    
    public RoleAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public RoleAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.RoleAlreadyRegisteredException roleAlreadyRegisteredException) {
        super(message);
        this.roleAlreadyRegisteredException = roleAlreadyRegisteredException;
    }

    public RoleAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.RoleAlreadyRegisteredException roleAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.roleAlreadyRegisteredException = roleAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.RoleAlreadyRegisteredException getFaultInfo() {
        return this.roleAlreadyRegisteredException;
    }
}
