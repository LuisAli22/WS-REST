package ar.com.learsoft;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ar.com.learsoft.soap.ServiceChecker;

@RestController
public class AfipController {
	private final static Logger LOGGER = Logger.getLogger("AfipController");
	
	private Long time() {
        String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        Long time = Long.parseLong(time2);
        return time;
    }
	
	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) throws MalformedURLException {
		//LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: ", client.getId());
		URL url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
		 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.learsoft.com.ar/", "ServiceChecker");
 
        Service service = Service.create(url, qname);
 
        ServiceChecker serviceOperation = service.getPort(ServiceChecker.class);
        String status= serviceOperation.getStatus();
		ServiceResponse serviceResponse= new ServiceResponse(this.time(),status);
		DataBase data = new DataBase();
        data.saveData(client, serviceResponse);
		return serviceResponse;
	}

}
