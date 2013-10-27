
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-27T16:08:11.477-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "EntityNotRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class EntityNotRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.EntityNotRegisteredException entityNotRegisteredException;

    public EntityNotRegisteredException_Exception() {
        super();
    }
    
    public EntityNotRegisteredException_Exception(String message) {
        super(message);
    }
    
    public EntityNotRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EntityNotRegisteredException entityNotRegisteredException) {
        super(message);
        this.entityNotRegisteredException = entityNotRegisteredException;
    }

    public EntityNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EntityNotRegisteredException entityNotRegisteredException, Throwable cause) {
        super(message, cause);
        this.entityNotRegisteredException = entityNotRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.EntityNotRegisteredException getFaultInfo() {
        return this.entityNotRegisteredException;
    }
}
