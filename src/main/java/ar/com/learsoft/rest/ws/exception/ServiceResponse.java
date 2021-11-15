package ar.com.learsoft.rest.ws.exception;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import ar.com.learsoft.rest.ws.model.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {

	private String status;
	private Long time;
	public ServiceResponse() {
		time = this.getCurrentInstant();
	}

	public ServiceResponse(String status) {
		this.status = status;
		time = this.getCurrentInstant();
	}

	public static List<ServiceResponse> setListResponse(List<ServiceStatus> serviceStatus) {
		List<ServiceResponse> listServiceResponse = new ArrayList<ServiceResponse>();
		for (ServiceStatus servStatus : serviceStatus) {
			GracefulInputResponse serviceResponse = new GracefulInputResponse();
			serviceResponse.setStatus(servStatus.getStatus());
			serviceResponse.setApplicationID(servStatus.getApplicationID());
			serviceResponse.setTime(servStatus.getTime());
			listServiceResponse.add(serviceResponse);
		}
		return listServiceResponse;
	}

	private Long getCurrentInstant() {
		Instant instant = Instant.now();
		return instant.toEpochMilli();
	}
}
