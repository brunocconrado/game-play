
package br.com.embracon.wsembraconvo.teamposition;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.embracon.wsembraconvo.teamposition package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.embracon.wsembraconvo.teamposition
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Collaborator }
     * 
     */
    public Collaborator createCollaborator() {
        return new Collaborator();
    }

    /**
     * Create an instance of {@link Regional }
     * 
     */
    public Regional createRegional() {
        return new Regional();
    }

    /**
     * Create an instance of {@link Branch }
     * 
     */
    public Branch createBranch() {
        return new Branch();
    }

}
