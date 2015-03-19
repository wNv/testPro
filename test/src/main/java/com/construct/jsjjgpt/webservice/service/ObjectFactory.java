
package com.construct.jsjjgpt.webservice.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.construct.jsjjgpt.webservice.service package. 
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

    private final static QName _ProcessDataResponse_QNAME = new QName("http://service.webservice.jsjjgpt.construct.com/", "processDataResponse");
    private final static QName _ProcessData_QNAME = new QName("http://service.webservice.jsjjgpt.construct.com/", "processData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.construct.jsjjgpt.webservice.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessData }
     * 
     */
    public ProcessData createProcessData() {
        return new ProcessData();
    }

    /**
     * Create an instance of {@link ProcessDataResponse }
     * 
     */
    public ProcessDataResponse createProcessDataResponse() {
        return new ProcessDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.jsjjgpt.construct.com/", name = "processDataResponse")
    public JAXBElement<ProcessDataResponse> createProcessDataResponse(ProcessDataResponse value) {
        return new JAXBElement<ProcessDataResponse>(_ProcessDataResponse_QNAME, ProcessDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.jsjjgpt.construct.com/", name = "processData")
    public JAXBElement<ProcessData> createProcessData(ProcessData value) {
        return new JAXBElement<ProcessData>(_ProcessData_QNAME, ProcessData.class, null, value);
    }

}
