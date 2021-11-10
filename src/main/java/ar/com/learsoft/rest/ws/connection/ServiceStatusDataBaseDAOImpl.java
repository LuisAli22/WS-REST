package ar.com.learsoft.rest.ws.connection;

import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.GracefulInputResponse;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

public class ServiceStatusDataBaseDAOImpl implements DAOServiceStatusDataBase {

	@Getter
	@Setter
	private ServiceStatus serviceStatus;
	private Client client;
	private GracefulInputResponse serviceResponse;

	public ServiceStatusDataBaseDAOImpl(ServiceStatus serviceStatus, Client client, GracefulInputResponse serviceResponse) {
		this.serviceStatus = serviceStatus;
		this.client = client;
		this.serviceResponse = serviceResponse;
	}

	@Override
	public ServiceStatus saveInDataBase() {

		this.serviceStatus.setIdCliente(client.getId());
		this.serviceStatus.setStatus(this.serviceResponse.getStatus());
		this.serviceStatus.setTime(serviceResponse.getTimestamp());
		return this.serviceStatus;

	}

}
