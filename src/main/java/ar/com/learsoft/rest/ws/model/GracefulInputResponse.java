package ar.com.learsoft.rest.ws.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GracefulInputResponse extends ServiceResponse {
	private long timestamp;
	private List<ServiceStatus> queryResults;

	public GracefulInputResponse(String status) {
		super(status);
		this.timestamp = this.time();
	}

	public GracefulInputResponse(String status, List<ServiceStatus> queryResults) {
		super(status);
		this.queryResults = queryResults;
		this.timestamp = this.time();
	}

	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}
}
