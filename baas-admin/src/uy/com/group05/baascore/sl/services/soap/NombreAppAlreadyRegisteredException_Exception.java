
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T00:17:48.690-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "NombreAppAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class NombreAppAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException nombreAppAlreadyRegisteredException;

    public NombreAppAlreadyRegisteredException_Exception() {
        super();
    }
    
    public NombreAppAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public NombreAppAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NombreAppAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException nombreAppAlreadyRegisteredException) {
        super(message);
        this.nombreAppAlreadyRegisteredException = nombreAppAlreadyRegisteredException;
    }

    public NombreAppAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException nombreAppAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.nombreAppAlreadyRegisteredException = nombreAppAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException getFaultInfo() {
        return this.nombreAppAlreadyRegisteredException;
    }
}
