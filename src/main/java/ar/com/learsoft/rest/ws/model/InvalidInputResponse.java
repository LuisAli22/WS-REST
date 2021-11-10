package ar.com.learsoft.rest.ws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidInputResponse extends ServiceResponse{
	private StringBuilder errorMessages; 

	public InvalidInputResponse(StringBuilder errorMessages) {
		super("CLIENTE INCORRECTO");
		this.errorMessages = errorMessages; 
	}

}
