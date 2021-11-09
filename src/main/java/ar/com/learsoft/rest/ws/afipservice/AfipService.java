package ar.com.learsoft.rest.ws.afipservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.logging.Level;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import javax.xml.namespace.QName;

import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseDAOImpl;
import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseRepository;
import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.ServiceResponse;
import ar.com.learsoft.rest.ws.model.ServiceResponseError;
import ar.com.learsoft.rest.ws.model.ServiceResponseGraceful;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import ar.com.learsoft.soap.ws.ServiceChecker;

@Service
public class AfipService {
	@Autowired
	private ServiceStatusDataBaseRepository serviceStatusDataBaseRepository;
	private ServiceChecker wsSoapProxy;
	
	public AfipService() {
		wsSoapProxy = getWsSoapProxy();
	}
	private ServiceChecker getWsSoapProxy() {
		URL url= null;
		try {
			url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QName qname = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerService");
		QName qname2 = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerPort");
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
		return service.getPort(qname2, ServiceChecker.class);
	}
	@Autowired
	private Validator validator;
	public ServiceResponse check(Client client) {

		Set<ConstraintViolation<Client>> violations = validator.validate(client);

		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Client> constraintViolation : violations) {
				sb.append(constraintViolation.getMessage());
			}
			return new ServiceResponseError(sb);
		}
		String status= wsSoapProxy.getStatus();
		ServiceResponseGraceful serviceResponse = new ServiceResponseGraceful(status);
		ServiceStatus serviceStatus = new ServiceStatus();
		ServiceStatusDataBaseDAOImpl serviceStatusDataBase = new ServiceStatusDataBaseDAOImpl(serviceStatus, client,
				serviceResponse);
		serviceStatusDataBaseRepository.save(serviceStatusDataBase.saveInDataBase());
		return serviceResponse;
	}

}
