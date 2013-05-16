
package br.com.embracon.wsteamposition.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsentity.domain.entity.teamposition.CollaboratorInfo;


/**
 * <p>Java class for findCollaboratorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findCollaboratorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://teamposition.entity.domain.wsentity.embracon.com.br/}collaboratorInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findCollaboratorResponse", propOrder = {
    "_return"
})
public class FindCollaboratorResponse {

    @XmlElement(name = "return")
    protected CollaboratorInfo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CollaboratorInfo }
     *     
     */
    public CollaboratorInfo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollaboratorInfo }
     *     
     */
    public void setReturn(CollaboratorInfo value) {
        this._return = value;
    }

}
