package ar.com.learsoft.rest.ws.exception;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.learsoft.rest.ws.model.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {

	private String status;
	private Long time = this.time();

	public ServiceResponse() {

	}

	public ServiceResponse(String status) {
		this.status = status;
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

	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}
}
