package ar.com.learsoft.rest.ws.afipservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import javax.xml.namespace.QName;

import ar.com.learsoft.rest.ws.AfipController;
import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseDAOImpl;
import ar.com.learsoft.rest.ws.exception.ServiceResponse;
import ar.com.learsoft.rest.ws.model.ApplicationIdAfipServiceQuery;
import ar.com.learsoft.rest.ws.model.BetweenDaysAndIdQuery;
import ar.com.learsoft.rest.ws.model.BetweenDaysQuery;
import ar.com.learsoft.soap.ws.ServiceChecker;

@Service
public class AfipService {

	@Autowired
	private Validator validator;

	@Autowired
	private ServiceStatusDataBaseDAOImpl serviceStatusDataBaseDAOImpl;
	
	private Logger logger = LogManager.getLogger(AfipService.class);

	private ServiceChecker getWsSoapProxy() {
		URL url = null;
		try {
			url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		QName serviceName = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerService");
		QName portName = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerPort");
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, serviceName);
		return service.getPort(portName, ServiceChecker.class);
	}

	private ServiceResponse getResponseFromWsSoap() {
		ServiceChecker wsSoapProxy = getWsSoapProxy();
		String afipServiceStatus = wsSoapProxy.getStatus();
		return new ServiceResponse(afipServiceStatus);
	}

	public ServiceResponse getResponseFromSoapAndStoreItInDataBase(String applicationId) {
		ServiceResponse serviceResponse = this.getResponseFromWsSoap();
		serviceStatusDataBaseDAOImpl.saveInDataBase(applicationId, serviceResponse);
		return serviceResponse;
	}

	public ServiceResponse check(String applicationid) {
		ApplicationIdAfipServiceQuery applicationIdAfipServiceQuery= new ApplicationIdAfipServiceQuery(); 
		applicationIdAfipServiceQuery.setApplicationID(applicationid);
		logger.info("Validando la consulta de chequeo de servicio");
		this.validateAfipServiceQuery(applicationIdAfipServiceQuery);
		return getResponseFromSoapAndStoreItInDataBase(applicationid);
	}

	private void validateAfipServiceQuery(ApplicationIdAfipServiceQuery applicationIdAfipServiceQuery) {
		Set<ConstraintViolation<ApplicationIdAfipServiceQuery>> violations = validator.validate(applicationIdAfipServiceQuery);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
	private void validateBetweenDaysAndIdQuery(BetweenDaysAndIdQuery betweenDaysAndIdQuery) {
		Set<ConstraintViolation<BetweenDaysAndIdQuery>> violations = validator.validate(betweenDaysAndIdQuery);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
	private void validateBetweenDaysQuery(BetweenDaysQuery betweenDaysQuery) {
		Set<ConstraintViolation<BetweenDaysQuery>> violations = validator.validate(betweenDaysQuery);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
	public List<ServiceResponse> searchByApplicationId(String applicationid) {
		ApplicationIdAfipServiceQuery applicationIdAfipServiceQuery= new ApplicationIdAfipServiceQuery(); 
		applicationIdAfipServiceQuery.setApplicationID(applicationid);
		logger.info("Validando la consulta de chequeo de servicio");
		this.validateAfipServiceQuery(applicationIdAfipServiceQuery);
		return serviceStatusDataBaseDAOImpl.searchByApplicationId(applicationid);
	}

	public List<ServiceResponse> findByDate(Map<String, String> findByDateQueryParams) throws ParseException {
		BetweenDaysQuery betweenDaysQuery= new BetweenDaysQuery(findByDateQueryParams);
		logger.info("Validando fechas ingresadas");
		this.validateBetweenDaysQuery(betweenDaysQuery);
		logger.info("Fechas validas!!");
		return serviceStatusDataBaseDAOImpl.findByDate(betweenDaysQuery);

	}
	public List<ServiceResponse> findByIdAndDate(Map<String, String> findByDateQueryParams) throws ParseException {
		BetweenDaysAndIdQuery betweenDaysAndIdQuery= new BetweenDaysAndIdQuery(findByDateQueryParams);
		logger.info("Validando applicationId y fechas ingresadas");
		this.validateBetweenDaysAndIdQuery(betweenDaysAndIdQuery);
		logger.info("ApplicationId y fechas validas!!");
		return serviceStatusDataBaseDAOImpl.findByIdAndDate(betweenDaysAndIdQuery);

	}

}
