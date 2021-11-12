package ar.com.learsoft.rest.ws;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.rest.ws.model.ServiceStatus;
import ar.com.learsoft.rest.ws.afipservice.AfipService;
import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.FindByDate;
import ar.com.learsoft.rest.ws.model.GracefulInputResponse;
import ar.com.learsoft.rest.ws.model.GracefulInputResponseQueryResult;
import ar.com.learsoft.rest.ws.model.InvalidInputResponse;
import ar.com.learsoft.rest.ws.model.ServiceResponse;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AfipController {
	@Autowired
	private AfipService afipService;

	@PostMapping("/checkservice")
	public ResponseEntity<ServiceResponse> checkService(@RequestBody Client client) {
		ServiceResponse serviceResponse = null;
		try {
			serviceResponse = afipService.check(client);
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

	@GetMapping("/findbyapplicationid/")
	public ResponseEntity<ServiceResponse> findByApplicationId(@RequestBody Client client) {
		GracefulInputResponseQueryResult gracefulInputResponseQueryResult = null;
		try {
			List<ServiceStatus> list = afipService.searchByApplicationId(client);
			String status = "Se encontraron resultados";
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(status, list);
		} catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			String status = "Consulta sin resultados";
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(status, message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		}
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}

	@GetMapping("/findbydate/{firstDate}/{secondDate}")
	public ResponseEntity<ServiceResponse>findByDate(@PathVariable Map<String,String> findByDateQueryParams){
		GracefulInputResponseQueryResult gracefulInputResponseQueryResult = null;
		try {
			List<ServiceStatus> List = afipService.findByDate(findByDateQueryParams);
			String status = "Se encontraron resultados";
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(status,List);
		}catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			String status = "Consulta sin resultados";
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(status, message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		}	
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}
	@GetMapping("/findbyidanddate/{applicationId}/{firstDate}/{secondDate}")
	public ResponseEntity<ServiceResponse>findByIdAndDate(@PathVariable Map<String,String> findByDateQueryParams){
		GracefulInputResponseQueryResult gracefulInputResponseQueryResult = null;
		try {
			List<ServiceStatus> List = afipService.findByIdAndDate(findByDateQueryParams);
			String status = "Se encontraron resultados";
			gracefulInputResponseQueryResult = new GracefulInputResponseQueryResult(status,List);
		}catch (ConstraintViolationException constraintViolationException) {
			String message = constraintViolationException.getMessage();
			String status = "Consulta sin resultados";
			InvalidInputResponse invalidInputResponse = new InvalidInputResponse(status, message);
			return ResponseEntity.badRequest().body(invalidInputResponse);
		}	
		return ResponseEntity.ok().body(gracefulInputResponseQueryResult);
	}
}
