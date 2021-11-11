package ar.com.learsoft.rest.ws.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ServiceResponse {
	private String status;
	


	public ServiceResponse(String status) {
		super();
		this.status = status;
	}
		


	}
	
	
