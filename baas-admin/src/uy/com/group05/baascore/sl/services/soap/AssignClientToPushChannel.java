
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignClientToPushChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignClientToPushChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCanal" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCliente" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignClientToPushChannel", propOrder = {
    "idCanal",
    "idCliente"
})
public class AssignClientToPushChannel {

    protected long idCanal;
    protected long idCliente;

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
     * Gets the value of the idCliente property.
     * 
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the value of the idCliente property.
     * 
     */
    public void setIdCliente(long value) {
        this.idCliente = value;
    }

}
