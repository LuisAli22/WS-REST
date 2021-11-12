package ar.com.learsoft.rest.ws.model;

import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
	@NotNull(message = "El identificador de la aplicación No puede ser nulo")
	private String applicationID;

}
