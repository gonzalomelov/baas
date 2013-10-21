
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permissionEntityDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="permissionEntityDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="has" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idEntity" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idOperation" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permissionEntityDTO", propOrder = {
    "has",
    "idEntity",
    "idOperation"
})
public class PermissionEntityDTO {

    protected boolean has;
    protected long idEntity;
    protected long idOperation;

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
     * Gets the value of the idEntity property.
     * 
     */
    public long getIdEntity() {
        return idEntity;
    }

    /**
     * Sets the value of the idEntity property.
     * 
     */
    public void setIdEntity(long value) {
        this.idEntity = value;
    }

    /**
     * Gets the value of the idOperation property.
     * 
     */
    public long getIdOperation() {
        return idOperation;
    }

    /**
     * Sets the value of the idOperation property.
     * 
     */
    public void setIdOperation(long value) {
        this.idOperation = value;
    }

}
