package ar.com.learsoft.rest.ws.afipservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import javax.xml.namespace.QName;

import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseDAOImpl;
import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseRepository;
import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.GracefulInputResponse;
import ar.com.learsoft.rest.ws.model.InvalidInputResponse;
import ar.com.learsoft.rest.ws.model.ServiceResponse;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import ar.com.learsoft.soap.ws.ServiceChecker;

@Service
public class AfipService {
	@Autowired
	private ServiceStatusDataBaseRepository serviceStatusDataBaseRepository;
	
	@Autowired
	private Validator validator;
	
	private ServiceChecker getWsSoapProxy() {
		URL url= null;
		try {
			url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QName serviceName = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerService");
		QName portName = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerPort");
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, serviceName);
		return service.getPort(portName, ServiceChecker.class);
	}
	
//	private StringBuilder getAllErrorMessages(Set<ConstraintViolation<Client>> violations) {
//		StringBuilder allErrorMessages = new StringBuilder();
//		for (ConstraintViolation<Client> constraintViolation : violations) {
//			allErrorMessages.append(constraintViolation.getMessage());
//		}
//		return allErrorMessages;
//	}
	private GracefulInputResponse getResponseFromWsSoap() {
		ServiceChecker wsSoapProxy= getWsSoapProxy();
		String afipServiceStatus= wsSoapProxy.getStatus();
		return new GracefulInputResponse(afipServiceStatus);
	}
	private void storeResultInDataBase(GracefulInputResponse gracefulInputResponse, Client client) {
		ServiceStatus serviceStatus = new ServiceStatus();
		ServiceStatusDataBaseDAOImpl serviceStatusDataBase = new ServiceStatusDataBaseDAOImpl(serviceStatus, client,
				gracefulInputResponse);
		serviceStatusDataBaseRepository.save(serviceStatusDataBase.saveInDataBase());
	}
	
	public GracefulInputResponse getResponseFromSoapAndStoreItInDataBase(Client client) {
		GracefulInputResponse gracefulInputResponse = this.getResponseFromWsSoap();
		storeResultInDataBase(gracefulInputResponse, client);
		return gracefulInputResponse;
	}

	public ServiceResponse check(Client client) {
		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		if (!violations.isEmpty()) {
//			StringBuilder allErrorMessages = this.getAllErrorMessages(violations);
//			return new InvalidInputResponse(allErrorMessages);
			throw new ConstraintViolationException(violations);

		}
		return getResponseFromSoapAndStoreItInDataBase(client);
	}

}
