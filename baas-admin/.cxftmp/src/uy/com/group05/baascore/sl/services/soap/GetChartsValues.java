
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getChartsValues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getChartsValues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipoChart" type="{http://soap.services.sl.baascore.group05.com.uy/}tipoChart" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getChartsValues", propOrder = {
    "idApp",
    "tipoChart"
})
public class GetChartsValues {

    protected long idApp;
    protected TipoChart tipoChart;

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
     * Gets the value of the tipoChart property.
     * 
     * @return
     *     possible object is
     *     {@link TipoChart }
     *     
     */
    public TipoChart getTipoChart() {
        return tipoChart;
    }

    /**
     * Sets the value of the tipoChart property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoChart }
     *     
     */
    public void setTipoChart(TipoChart value) {
        this.tipoChart = value;
    }

}
