
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-03T12:30:40.655-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "EntityAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class EntityAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.EntityAlreadyRegisteredException entityAlreadyRegisteredException;

    public EntityAlreadyRegisteredException_Exception() {
        super();
    }
    
    public EntityAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public EntityAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EntityAlreadyRegisteredException entityAlreadyRegisteredException) {
        super(message);
        this.entityAlreadyRegisteredException = entityAlreadyRegisteredException;
    }

    public EntityAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EntityAlreadyRegisteredException entityAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.entityAlreadyRegisteredException = entityAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.EntityAlreadyRegisteredException getFaultInfo() {
        return this.entityAlreadyRegisteredException;
    }
}
