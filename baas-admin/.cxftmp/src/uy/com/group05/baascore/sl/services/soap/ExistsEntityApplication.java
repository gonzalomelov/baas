
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsEntityApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="existsEntityApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "existsEntityApplication", propOrder = {
    "nomApp",
    "nomEntity"
})
public class ExistsEntityApplication {

    protected String nomApp;
    protected String nomEntity;

    /**
     * Gets the value of the nomApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomApp() {
        return nomApp;
    }

    /**
     * Sets the value of the nomApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomApp(String value) {
        this.nomApp = value;
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
