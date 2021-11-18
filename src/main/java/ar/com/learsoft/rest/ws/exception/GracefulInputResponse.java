package ar.com.learsoft.rest.ws.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GracefulInputResponse extends ServiceResponse {

	private String applicationID;

	public GracefulInputResponse(String status) {
		super(status);
	}

	public GracefulInputResponse() {

	}
}
