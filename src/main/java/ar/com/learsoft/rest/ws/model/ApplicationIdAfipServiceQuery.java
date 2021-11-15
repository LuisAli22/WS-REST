package ar.com.learsoft.rest.ws.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationIdAfipServiceQuery {
	@NotNull(message = "No está ingresando applicationid. Actualmente tiene el valor nulo")
	@Pattern(regexp = "^[a-z]+$", message = "applicationid debe estar formado por letras en minúscula.")
	private String applicationID;

}
