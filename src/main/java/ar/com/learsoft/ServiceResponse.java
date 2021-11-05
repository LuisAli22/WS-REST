package ar.com.learsoft;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {
	private long timestamp;
	private String status;
	
	
	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;

	}

}
