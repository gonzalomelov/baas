
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPermissionsForRol complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPermissionsForRol">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rolId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPermissionsForRol", propOrder = {
    "appId",
    "rolId"
})
public class GetPermissionsForRol {

    protected long appId;
    protected long rolId;

    /**
     * Gets the value of the appId property.
     * 
     */
    public long getAppId() {
        return appId;
    }

    /**
     * Sets the value of the appId property.
     * 
     */
    public void setAppId(long value) {
        this.appId = value;
    }

    /**
     * Gets the value of the rolId property.
     * 
     */
    public long getRolId() {
        return rolId;
    }

    /**
     * Sets the value of the rolId property.
     * 
     */
    public void setRolId(long value) {
        this.rolId = value;
    }

}
