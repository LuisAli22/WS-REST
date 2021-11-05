package ar.com.learsoft;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AfipController {
	@Autowired
	private DataBaseRepository dataBaseRepository;
	
	
	private final static Logger LOGGER = Logger.getLogger("AfipController");
	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) {
		LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: ", client.getId());
		ServiceResponse serviceResponse= new ServiceResponse();
		serviceResponse.setTimestamp(new Date().getTime());
		DataBase data = new DataBase();
		data.saveData(client, serviceResponse);
		dataBaseRepository.save(data);
		return serviceResponse;
	}

}
