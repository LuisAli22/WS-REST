package ar.com.learsoft.rest.ws.connection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.learsoft.rest.ws.model.Client;
import ar.com.learsoft.rest.ws.model.GracefulInputResponse;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import lombok.Getter;
import lombok.Setter;
@Service
public class ServiceStatusDataBaseDAOImpl {

	@Getter
	@Setter
	private ServiceStatus serviceStatus;
	
	@Autowired
	private ServiceStatusDataBaseRepository serviceStatusDataBaseRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	public void saveInDataBase(Client client, GracefulInputResponse serviceResponse) {
		this.serviceStatus= new ServiceStatus();
		this.serviceStatus.setApplicationID(client.getApplicationID());
		this.serviceStatus.setStatus(serviceResponse.getStatus());
		this.serviceStatus.setTime(serviceResponse.getTimestamp());
		serviceStatusDataBaseRepository.save(this.serviceStatus);
	}

	public ServiceStatusDataBaseDAOImpl() {
		super();
	}
	
	public List<ServiceStatus> searchByApplicationId(Client client) {            
		String hql = "SELECT e FROM ServiceStatus e WHERE APPLICATION_ID IN ?1";                        
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class).setParameter(1, client.getApplicationID());
		return query.getResultList();
	}
	
	
	public List<ServiceStatus>findByDate(Long firstDate, Long secondDate) {
		String hql = "SELECT e FROM ServiceStatus e WHERE TIME BETWEEN ?1 AND ?2";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class);
		query.setParameter(1, firstDate);
		query.setParameter(2, secondDate);
		return query.getResultList();
	}
}
