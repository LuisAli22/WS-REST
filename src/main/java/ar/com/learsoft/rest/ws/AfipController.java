package ar.com.learsoft.rest.ws;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.rest.ws.afipservice.AfipService;
import ar.com.learsoft.rest.ws.exception.GracefulInputResponseQueryResult;
import ar.com.learsoft.rest.ws.exception.GracefulInputResponseQueryResultException;
import ar.com.learsoft.rest.ws.exception.InvalidInputResponse;
import ar.com.learsoft.rest.ws.exception.ServiceResponse;
import ar.com.learsoft.rest.ws.model.BetweenDaysQuery;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.xml.ws.WebServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class AfipController {
	@Autowired
	private AfipService afipService;
	private Logger logger = LogManager.getLogger(AfipController.class);

	@PostMapping("/checkservice/{applicationid}")
	public ResponseEntity<ServiceResponse> checkService(@PathVariable String applicationid) {
		ServiceResponse serviceResponse = null;
		try {
			serviceResponse = afipService.check(applicationid);
		} catch (WebServiceException webServiceException) {
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(webServiceException.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(invalidInputResponse);
		} catch (ConstraintViolationException constraintViolationException) {
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(
					constraintViolationException.getMessage());
			return ResponseEntity.badRequest().body(invalidInputResponse);
		}
		return ResponseEntity.ok().body(serviceResponse);
	}

	@GetMapping("/findbyapplicationid/{applicationid}")
	public ResponseEntity<ServiceResponse> findByApplicationId(@PathVariable String applicationid) {
		ServiceResponse gracefulInputResponseQueryResult = null;
		try {
			List<ServiceResponse> list = afipService.searchByApplicationId(applicationid);
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(list);
		} catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		} catch (GracefulInputResponseQueryResultException gracefulInputResponseQueryResultsNullResults) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}

	@GetMapping("/findbydate/{firstDate}/{secondDate}")
	public ResponseEntity<ServiceResponse> findByDate(@PathVariable Map<String, String> findByDateQueryParams) {
		GracefulInputResponseQueryResult gracefulInputResponseQueryResult = null;
		try {
			List<ServiceResponse> list = afipService.findByDate(findByDateQueryParams);
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(list);
		}catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		}  catch (GracefulInputResponseQueryResultException gracefulInputResponseQueryResultNullResults) {
			String message = gracefulInputResponseQueryResultNullResults.getMessage();
			logger.info(message);
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(message);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(invalidInputResponse);
		} catch (ParseException parseException) {
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(parseException.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(invalidInputResponse);
		}
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}

	@GetMapping("/findbyidanddate/{applicationId}/{firstDate}/{secondDate}")
	public ResponseEntity<ServiceResponse> findByIdAndDate(@PathVariable Map<String, String> findByDateQueryParams) {
		GracefulInputResponseQueryResult gracefulInputResponseQueryResult = null;
		try {
			List<ServiceResponse> list = afipService.findByIdAndDate(findByDateQueryParams);
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(list);
		} catch (GracefulInputResponseQueryResultException gracefulInputResponseQueryResultNullResults) {
			String message = gracefulInputResponseQueryResultNullResults.getMessage();
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(message);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(invalidInputResponse);
		}catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		} catch (ParseException parseException) {
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(parseException.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(invalidInputResponse);
		}
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}
}
