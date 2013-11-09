
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoChart.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoChart">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Minutos"/>
 *     &lt;enumeration value="Horas"/>
 *     &lt;enumeration value="Dias"/>
 *     &lt;enumeration value="Mes"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoChart")
@XmlEnum
public enum TipoChart {

    @XmlEnumValue("Minutos")
    MINUTOS("Minutos"),
    @XmlEnumValue("Horas")
    HORAS("Horas"),
    @XmlEnumValue("Dias")
    DIAS("Dias"),
    @XmlEnumValue("Mes")
    MES("Mes");
    private final String value;

    TipoChart(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoChart fromValue(String v) {
        for (TipoChart c: TipoChart.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
