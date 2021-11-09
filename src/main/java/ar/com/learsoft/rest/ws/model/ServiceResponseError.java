package ar.com.learsoft.rest.ws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponseError extends ServiceResponse{
	private StringBuilder errorMessages; 

	public ServiceResponseError(StringBuilder errorMessages) {
		super("CLIENTE INCORRECTO");
		this.errorMessages = errorMessages; 
	}

}
