package ar.com.learsoft.rest.ws.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidInputResponse extends ServiceResponse{
	private String message; 
	

	public InvalidInputResponse(String message) {
		super("CLIENTE INCORRECTO");
		this.message = message; 
	}

	public InvalidInputResponse(String status, String message) {
		super(status);
		this.message = message;
	}

}
