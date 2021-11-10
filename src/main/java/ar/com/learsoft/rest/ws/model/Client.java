package ar.com.learsoft.rest.ws.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
	@NotNull(message = "El identificador de la aplicación No puede ser nulo")
	@Pattern(regexp = "^(10|[1-9])$", message = "El identificador de la aplicacion debe ser un número  >=1 y <=10")
	private String applicationId;
}
