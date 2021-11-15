package ar.com.learsoft.rest.ws.exception;


import ar.com.learsoft.rest.ws.util.Definitions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidInputResponse extends ServiceResponse{
	private String message; 
	

	public InvalidInputResponse(String message) {
		super(Definitions.INVALID_INPUT_STATUS);
		this.message = message; 
	}

	public InvalidInputResponse(String status, String message) {
		super(status);
		this.message = message;
	}

}
