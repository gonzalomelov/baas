
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rolesClientDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rolesClientDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="has" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idRole" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rolesClientDTO", propOrder = {
    "has",
    "idRole"
})
public class RolesClientDTO {

    protected boolean has;
    protected long idRole;

    /**
     * Gets the value of the has property.
     * 
     */
    public boolean isHas() {
        return has;
    }

    /**
     * Sets the value of the has property.
     * 
     */
    public void setHas(boolean value) {
        this.has = value;
    }

    /**
     * Gets the value of the idRole property.
     * 
     */
    public long getIdRole() {
        return idRole;
    }

    /**
     * Sets the value of the idRole property.
     * 
     */
    public void setIdRole(long value) {
        this.idRole = value;
    }

}
