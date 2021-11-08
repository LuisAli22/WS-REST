package ar.com.learsoft.rest.ws;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseDAOImpl;
import ar.com.learsoft.rest.ws.connection.ServiceStatusDataBaseRepository;
import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.ServiceResponse;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AfipController {
	@Autowired
	private ServiceStatusDataBaseRepository serviceStatusDataBaseRepository;

	private final static Logger LOGGER = Logger.getLogger("AfipController");

	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) throws MalformedURLException {
		LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: " + client.getId());
		ServiceStatusRequest serviceStatusRequest = new ServiceStatusRequest();
		ServiceResponse serviceResponse = new ServiceResponse(serviceStatusRequest.getStatus());
		ServiceStatus serviceStatus = new ServiceStatus();
		ServiceStatusDataBaseDAOImpl serviceStatusDataBase = new ServiceStatusDataBaseDAOImpl(serviceStatus, client,
				serviceResponse);
		serviceStatusDataBaseRepository.save(serviceStatusDataBase.saveInDataBase());
		return serviceResponse;
	}
}
