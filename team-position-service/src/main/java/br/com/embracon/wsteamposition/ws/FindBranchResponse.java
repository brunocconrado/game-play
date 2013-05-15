
package br.com.embracon.wsteamposition.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsentity.domain.entity.teamposition.BranchInfo;


/**
 * <p>Java class for findBranchResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findBranchResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://teamposition.entity.domain.wsentity.embracon.com.br/}branchInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findBranchResponse", propOrder = {
    "_return"
})
public class FindBranchResponse {

    @XmlElement(name = "return")
    protected BranchInfo _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link BranchInfo }
     *     
     */
    public BranchInfo getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchInfo }
     *     
     */
    public void setReturn(BranchInfo value) {
        this._return = value;
    }

}
