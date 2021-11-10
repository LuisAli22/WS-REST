package ar.com.learsoft.rest.ws;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.rest.ws.afipservice.AfipService;
import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.ServiceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AfipController {
	@Autowired
    private AfipService afipService;

	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) {
		return afipService.check(client);
	}
}
