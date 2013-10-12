
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editEntityApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editEntityApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nomEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editEntityApplication", propOrder = {
    "idApp",
    "idUser",
    "nomEntity"
})
public class EditEntityApplication {

    protected long idApp;
    protected long idUser;
    protected String nomEntity;

    /**
     * Gets the value of the idApp property.
     * 
     */
    public long getIdApp() {
        return idApp;
    }

    /**
     * Sets the value of the idApp property.
     * 
     */
    public void setIdApp(long value) {
        this.idApp = value;
    }

    /**
     * Gets the value of the idUser property.
     * 
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     * 
     */
    public void setIdUser(long value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the nomEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEntity() {
        return nomEntity;
    }

    /**
     * Sets the value of the nomEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEntity(String value) {
        this.nomEntity = value;
    }

}
