package ar.com.learsoft.rest.ws.model;



import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GracefulInputResponse extends ServiceResponse {
	private Long timestamp;

	public GracefulInputResponse(String status) {
		super(status);
		this.timestamp = time();
		}

	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}
}
