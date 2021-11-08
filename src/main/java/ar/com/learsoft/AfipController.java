package ar.com.learsoft;

import org.springframework.web.bind.annotation.RestController;

import ar.com.learsoft.soap.ws.ServiceChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@RestController
public class AfipController {
	@Autowired
	private DataBaseRepository dataBaseRepository;
	private String status;
	private Service service;
	private ServiceChecker serviceOperation;
	private QName qname;
	private QName qname2;
	private URL url;

	private Long time() {
		String time2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
		Long time = Long.parseLong(time2);
		return time;
	}

	private final static Logger LOGGER = Logger.getLogger("AfipController");

	@PostMapping("/checkservice")
	public ServiceResponse checkService(@RequestBody Client client) throws MalformedURLException {
		LOGGER.log(Level.INFO, "AfipController (checkService): Recibo el id: " + client.getId());
		ServiceResponse serviceResponse = new ServiceResponse(this.time(), this.getStatus());
		DataBase data = new DataBase();
		data.saveData(client, serviceResponse);
		dataBaseRepository.save(data);
		return serviceResponse;
	}

	public String getStatus() {
		try {
			url = new URL("http://localhost:8080/ws/afipchecker?wsdl");
			qname = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerService");
			qname2 = new QName("http://ws.soap.learsoft.com.ar/", "AfipServiceCheckerPort");
			service = Service.create(url, qname);
			serviceOperation = service.getPort(qname2, ServiceChecker.class);
			status = serviceOperation.getStatus();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return status;
	}

}
