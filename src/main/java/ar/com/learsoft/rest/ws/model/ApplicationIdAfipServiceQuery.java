package ar.com.learsoft.rest.ws.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationIdAfipServiceQuery {
	@NotNull(message = "El identificador de la aplicación No puede ser nulo")
	@Pattern(regexp = "^[a-z]+$", message = "El identificador de la aplicacion debe ser una cadena en minúscula")
	private String applicationID;

}
