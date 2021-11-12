package ar.com.learsoft.rest.ws.exception;

import java.util.List;

import ar.com.learsoft.rest.ws.util.Definitions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GracefulInputResponseQueryResult extends ServiceResponse {
	private List<ServiceResponse> queryResult;
	private String status;

	public GracefulInputResponseQueryResult(List<ServiceResponse> list)
			throws GracefulInputResponseQueryResultNullException {
		status = Definitions.REQUEST_WITH_RESULTS;
		this.queryResult = list;
		if (this.queryResult.size() == 0) {
			throw new GracefulInputResponseQueryResultNullException(Definitions.NULL_EXCEPTION);

		}

	}

}
