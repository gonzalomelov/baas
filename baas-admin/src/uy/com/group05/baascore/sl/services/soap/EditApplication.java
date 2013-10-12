
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rolStr" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entidadStr" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editApplication", propOrder = {
    "nombreApp",
    "rolStr",
    "entidadStr"
})
public class EditApplication {

    protected String nombreApp;
    protected List<String> rolStr;
    protected List<String> entidadStr;

    /**
     * Gets the value of the nombreApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreApp() {
        return nombreApp;
    }

    /**
     * Sets the value of the nombreApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreApp(String value) {
        this.nombreApp = value;
    }

    /**
     * Gets the value of the rolStr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rolStr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRolStr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRolStr() {
        if (rolStr == null) {
            rolStr = new ArrayList<String>();
        }
        return this.rolStr;
    }

    /**
     * Gets the value of the entidadStr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entidadStr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntidadStr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEntidadStr() {
        if (entidadStr == null) {
            entidadStr = new ArrayList<String>();
        }
        return this.entidadStr;
    }

}
