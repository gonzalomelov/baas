
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for savePushChannelEntities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="savePushChannelEntities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCanal" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="entity" type="{http://soap.services.sl.baascore.group05.com.uy/}simplePushChannelEntityDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "savePushChannelEntities", propOrder = {
    "idApp",
    "idCanal",
    "entity"
})
public class SavePushChannelEntities {

    protected long idApp;
    protected long idCanal;
    protected List<SimplePushChannelEntityDTO> entity;

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
     * Gets the value of the idCanal property.
     * 
     */
    public long getIdCanal() {
        return idCanal;
    }

    /**
     * Sets the value of the idCanal property.
     * 
     */
    public void setIdCanal(long value) {
        this.idCanal = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimplePushChannelEntityDTO }
     * 
     * 
     */
    public List<SimplePushChannelEntityDTO> getEntity() {
        if (entity == null) {
            entity = new ArrayList<SimplePushChannelEntityDTO>();
        }
        return this.entity;
    }

}
