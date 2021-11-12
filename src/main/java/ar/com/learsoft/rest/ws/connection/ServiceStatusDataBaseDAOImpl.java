package ar.com.learsoft.rest.ws.connection;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.learsoft.rest.ws.exception.ServiceResponse;
import ar.com.learsoft.rest.ws.model.Client;
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

	public ServiceStatusDataBaseDAOImpl() {
		super();
	}

	public void saveInDataBase(Client client, ServiceResponse serviceResponse) {
		this.serviceStatus = new ServiceStatus();
		this.serviceStatus.setApplicationID(client.getApplicationID().toLowerCase());
		this.serviceStatus.setStatus(serviceResponse.getStatus());
		this.serviceStatus.setTime(serviceResponse.getTime());
		serviceStatusDataBaseRepository.save(this.serviceStatus);
	}

	public List<ServiceResponse> searchByApplicationId(String applicationid) {
		applicationid.toLowerCase();
		String hql = "SELECT e FROM ServiceStatus e WHERE APPLICATION_ID IN ?1";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class).setParameter(1,
				applicationid);
		return ServiceResponse.setListResponse(query.getResultList());
	}

	public List<ServiceResponse> findByDate(Map<String, String> findByDateQueryParams) {
		String firstDate = findByDateQueryParams.get("firstDate");
		String secondDate = findByDateQueryParams.get("secondDate");
		String hql = "SELECT e FROM ServiceStatus e WHERE TIME BETWEEN ?1 AND ?2";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class);
		query.setParameter(1, firstDate);
		query.setParameter(2, secondDate);
		return ServiceResponse.setListResponse(query.getResultList());
	}

	public List<ServiceResponse> findByIdAndDate(Map<String, String> findByDateQueryParams) {
		String applicationId = findByDateQueryParams.get("applicationId");
		String firstDate = findByDateQueryParams.get("firstDate");
		String secondDate = findByDateQueryParams.get("secondDate");
		String hql = "SELECT e FROM ServiceStatus e WHERE APPLICATION_ID= ?1 AND TIME BETWEEN ?2 AND ?3";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class);
		query.setParameter(1, applicationId);
		query.setParameter(2, firstDate);
		query.setParameter(3, secondDate);
		return ServiceResponse.setListResponse(query.getResultList());
	}
}
