package ar.com.learsoft.rest.ws.connection;

import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.ServiceResponse;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

public class ServiceStatusDataBaseDAOImpl implements DAOServiceStatusDataBase {

	@Getter
	@Setter
	private ServiceStatus serviceStatus;
	private Client client;
	private ServiceResponse serviceResponse;

	public ServiceStatusDataBaseDAOImpl(ServiceStatus serviceStatus, Client client, ServiceResponse serviceResponse) {
		this.serviceStatus = serviceStatus;
		this.client = client;
		this.serviceResponse = serviceResponse;
	}

	@Override
	public ServiceStatus saveInDataBase() {

		this.serviceStatus.setID(this.client.getId());
		this.serviceStatus.setStatus(this.serviceResponse.getStatus());
		this.serviceStatus.setTime(serviceResponse.getTimestamp());
		return this.serviceStatus;

	}

}
