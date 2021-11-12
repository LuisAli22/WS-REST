package ar.com.learsoft.rest.ws.exception;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@SuppressWarnings("serial")
public class GracefulInputResponseQueryResultException extends Exception {
	private String message;
	
	public GracefulInputResponseQueryResultException(String message) {
		super();
		this.message = message;
	}
	
	
}
