package ar.com.learsoft.rest.ws.exception;

@SuppressWarnings("serial")
public class InvalidApplicationIdentierException extends Exception {

	public InvalidApplicationIdentierException(String message) {
		
		super("Identificador invalido: "+message);
	}

}
