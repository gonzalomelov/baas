
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permissionDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="permissionDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="application" type="{http://soap.services.sl.baascore.group05.com.uy/}applicationDTO" minOccurs="0"/>
 *         &lt;element name="entity" type="{http://soap.services.sl.baascore.group05.com.uy/}entityDTO" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="operation" type="{http://soap.services.sl.baascore.group05.com.uy/}operationDTO" minOccurs="0"/>
 *         &lt;element name="role" type="{http://soap.services.sl.baascore.group05.com.uy/}roleDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permissionDTO", propOrder = {
    "application",
    "entity",
    "id",
    "operation",
    "role"
})
public class PermissionDTO {

    protected ApplicationDTO application;
    protected EntityDTO entity;
    protected long id;
    protected OperationDTO operation;
    protected RoleDTO role;

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationDTO }
     *     
     */
    public ApplicationDTO getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationDTO }
     *     
     */
    public void setApplication(ApplicationDTO value) {
        this.application = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link EntityDTO }
     *     
     */
    public EntityDTO getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityDTO }
     *     
     */
    public void setEntity(EntityDTO value) {
        this.entity = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link OperationDTO }
     *     
     */
    public OperationDTO getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationDTO }
     *     
     */
    public void setOperation(OperationDTO value) {
        this.operation = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link RoleDTO }
     *     
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleDTO }
     *     
     */
    public void setRole(RoleDTO value) {
        this.role = value;
    }

}
