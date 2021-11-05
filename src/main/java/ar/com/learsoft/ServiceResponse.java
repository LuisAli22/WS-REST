package ar.com.learsoft;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {
	private long timestamp;
	private String status;
	public ServiceResponse(long timestamp, String status) {
		this.timestamp = timestamp;
		this.status = status;
	}
	
}
