package ar.com.learsoft.rest.ws.model;

import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.learsoft.rest.ws.afipservice.AfipService;
import ar.com.learsoft.rest.ws.util.Definitions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetweenDaysAndIdQuery extends BetweenDaysQuery{
	@Valid
	ApplicationIdAfipServiceQuery applicationIdAfipServiceQuery;

	private Logger logger = LogManager.getLogger(BetweenDaysAndIdQuery.class);
	
	public BetweenDaysAndIdQuery(Map<String, String> findByDateQueryParams) throws ParseException {
		super(findByDateQueryParams); 
		logger.info(findByDateQueryParams);
		applicationIdAfipServiceQuery= new ApplicationIdAfipServiceQuery(); 
		String applicationId= findByDateQueryParams.get(Definitions.APPLICATIONID_LABEL);
		applicationIdAfipServiceQuery.setApplicationID(applicationId);
	}
	
	public String getApplicationId() {
		return applicationIdAfipServiceQuery.getApplicationID();
	}

}
