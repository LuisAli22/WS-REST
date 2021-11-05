package ar.com.learsoft;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.soap.ServiceChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ar.com.learsoft.soap.ServiceChecker;

@RestController
public class AfipController {
	@Autowired
	private DataBaseRepository dataBaseRepository;
	
	private Long time() {
        String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        Long time = Long.parseLong(time2);
        return time;
    }
	
	private final static Logger LOGGER = Logger.getLogger("AfipController");
	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) {
		LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: " + client.getId());
		URL url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
		 
        QName qname = new QName("http://soap.learsoft.com.ar/", "ServiceChecker");
 
        Service service = Service.create(url, qname);
 
        ServiceChecker serviceOperation = service.getPort(ServiceChecker.class);
        String status= serviceOperation.getStatus();
		ServiceResponse serviceResponse= new ServiceResponse(this.time(),status);
		DataBase data = new DataBase();
        data.saveData(client, serviceResponse);
		dataBaseRepository.save(data);
		return serviceResponse;
	}

}
