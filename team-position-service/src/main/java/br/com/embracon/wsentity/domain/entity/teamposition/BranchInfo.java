
package br.com.embracon.wsentity.domain.entity.teamposition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsentity.domain.Branch;


/**
 * <p>Java class for branchInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="branchInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://domain.wsentity.embracon.com.br/}branch">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "branchInfo")
public class BranchInfo
    extends Branch
{


}
