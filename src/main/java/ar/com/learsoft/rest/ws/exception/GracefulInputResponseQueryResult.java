package ar.com.learsoft.rest.ws.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.com.learsoft.rest.ws.model.ServiceStatus;
import ar.com.learsoft.rest.ws.util.Definitions;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class GracefulInputResponseQueryResult extends ServiceResponse {
	private List<ServiceStatus> queryResult;
	private Long timestamp;
	
	public GracefulInputResponseQueryResult(String status, List<ServiceStatus> queryResult) throws GracefulInputResponseQueryResultNullException {
		super(status);
		this.queryResult = queryResult;
		System.out.println(queryResult);
		if(this.queryResult.size() == 0) {
			throw new GracefulInputResponseQueryResultNullException(Definitions.NULL_EXCEPTION);
		}
		this.timestamp = this.time();
	}





	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}

}
