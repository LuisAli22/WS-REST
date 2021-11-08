package ar.com.learsoft.rest.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ar.com.learsoft.soap.ws.ServiceChecker;

public class ServiceStatusRequest {
	private ServiceChecker serviceOperation;
	private QName qname;
	private QName qname2;
	private URL url;
	private String status;

	public String getStatus() {
		try {
			url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
			qname = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerService");
			qname2 = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerPort");
			Service service = Service.create(url, qname);
			serviceOperation = service.getPort(qname2, ServiceChecker.class);
			status = serviceOperation.getStatus();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return status;

	}

}