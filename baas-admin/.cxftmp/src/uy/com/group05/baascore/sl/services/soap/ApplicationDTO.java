
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applicationDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applicationDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiClientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clients" type="{http://soap.services.sl.baascore.group05.com.uy/}clientDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entities" type="{http://soap.services.sl.baascore.group05.com.uy/}entityDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roles" type="{http://soap.services.sl.baascore.group05.com.uy/}roleDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="users" type="{http://soap.services.sl.baascore.group05.com.uy/}userDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationDTO", propOrder = {
    "apiClientId",
    "clients",
    "entities",
    "id",
    "name",
    "roles",
    "users"
})
public class ApplicationDTO {

    protected String apiClientId;
    @XmlElement(nillable = true)
    protected List<ClientDTO> clients;
    @XmlElement(nillable = true)
    protected List<EntityDTO> entities;
    protected long id;
    protected String name;
    @XmlElement(nillable = true)
    protected List<RoleDTO> roles;
    @XmlElement(nillable = true)
    protected List<UserDTO> users;

    /**
     * Gets the value of the apiClientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiClientId() {
        return apiClientId;
    }

    /**
     * Sets the value of the apiClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiClientId(String value) {
        this.apiClientId = value;
    }

    /**
     * Gets the value of the clients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClientDTO }
     * 
     * 
     */
    public List<ClientDTO> getClients() {
        if (clients == null) {
            clients = new ArrayList<ClientDTO>();
        }
        return this.clients;
    }

    /**
     * Gets the value of the entities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityDTO }
     * 
     * 
     */
    public List<EntityDTO> getEntities() {
        if (entities == null) {
            entities = new ArrayList<EntityDTO>();
        }
        return this.entities;
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleDTO }
     * 
     * 
     */
    public List<RoleDTO> getRoles() {
        if (roles == null) {
            roles = new ArrayList<RoleDTO>();
        }
        return this.roles;
    }

    /**
     * Gets the value of the users property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the users property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserDTO }
     * 
     * 
     */
    public List<UserDTO> getUsers() {
        if (users == null) {
            users = new ArrayList<UserDTO>();
        }
        return this.users;
    }

}
