package com.construct.jsjjgpt.webservice.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class TestWebService {

	public static void main(String[] args) {

	//	ProjdataService ps=new ProjdataService();
		//ps.getProjdataPort();
		/*JaxWsDynamicClientFactory dynamicClient = JaxWsDynamicClientFactory.newInstance();  
		Client client = dynamicClient.createClient("http://172.16.4.173:8080/JSJJGPTService/service/Projdata?wsdl");  */
		 ProjdataService p=new ProjdataService();
		 Projdata oo=p.getProjdataPort();
		 oo.processData(reqPacket);
	}

}
