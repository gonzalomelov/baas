
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T06:16:18.261-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "PushChanAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class PushChanAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.PushChanAlreadyRegisteredException pushChanAlreadyRegisteredException;

    public PushChanAlreadyRegisteredException_Exception() {
        super();
    }
    
    public PushChanAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public PushChanAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public PushChanAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.PushChanAlreadyRegisteredException pushChanAlreadyRegisteredException) {
        super(message);
        this.pushChanAlreadyRegisteredException = pushChanAlreadyRegisteredException;
    }

    public PushChanAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.PushChanAlreadyRegisteredException pushChanAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.pushChanAlreadyRegisteredException = pushChanAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.PushChanAlreadyRegisteredException getFaultInfo() {
        return this.pushChanAlreadyRegisteredException;
    }
}
