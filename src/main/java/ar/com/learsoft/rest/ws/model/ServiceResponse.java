package ar.com.learsoft.rest.ws.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ServiceResponse {
	private String status;

	public ServiceResponse(String status) {
		super();
		this.status = status;
	}
}
