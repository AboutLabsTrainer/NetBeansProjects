package com.tutego.insel.xml.jaxb.bible;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tutego.insel.xml.jaxb.bible package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tutego.insel.xml.jaxb.bible
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bible }
     * 
     */
    public Bible createBible() {
        return new Bible();
    }

    /**
     * Create an instance of {@link Bible.Range }
     * 
     */
    public Bible.Range createBibleRange() {
        return new Bible.Range();
    }

    /**
     * Create an instance of {@link Bible.Range.Item }
     * 
     */
    public Bible.Range.Item createBibleRangeItem() {
        return new Bible.Range.Item();
    }

}
