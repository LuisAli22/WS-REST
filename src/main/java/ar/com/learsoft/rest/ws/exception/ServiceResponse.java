package ar.com.learsoft.rest.ws.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {
	private String status;
	


	public ServiceResponse(String status) {
		this.status = status;
	}
		


	}
	
	
