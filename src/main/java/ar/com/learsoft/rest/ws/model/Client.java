package ar.com.learsoft.rest.ws.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
	@Min(value = 1, message = "El identificador de cliente debe ser mayor a cero")
	@Max(value = 10, message = "El identificador de cliente debe ser menor o igual a 10")
	@NotNull(message = "El identificador del cliente es numerico. No puede ser nulo")
	private int id;
}
