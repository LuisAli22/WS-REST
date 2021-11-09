package ar.com.learsoft.rest.ws.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponseGraceful extends ServiceResponse {
	private long timestamp;

	public ServiceResponseGraceful(String status) {
		super(status);
		this.timestamp = this.time();
	}
	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}
}
