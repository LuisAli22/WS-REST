package ar.com.learsoft.rest.ws.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GracefulInputResponseQueryResult extends ServiceResponse {
	private List<ServiceStatus> queryResult;
	private Long timestamp;
	
	public GracefulInputResponseQueryResult(String status, List<ServiceStatus> queryResult) {
		super(status);
		this.queryResult = queryResult;
		this.timestamp = this.time();
	}





	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}

}
