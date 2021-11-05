package ar.com.learsoft;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AfipController {
	private final static Logger LOGGER = Logger.getLogger("AfipController");
	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody int clientId) {
		LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: ", clientId);
		ServiceResponse serviceResponse= new ServiceResponse();
		return serviceResponse;
	}

}
