package ar.com.learsoft.rest.ws.connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.learsoft.rest.ws.exception.ServiceResponse;
import ar.com.learsoft.rest.ws.model.BetweenDaysAndIdQuery;
import ar.com.learsoft.rest.ws.model.BetweenDaysQuery;
import ar.com.learsoft.rest.ws.model.ServiceStatus;
import ar.com.learsoft.rest.ws.util.Definitions;
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

	private Logger logger = LogManager.getLogger(ServiceStatusDataBaseDAOImpl.class);
	
	public ServiceStatusDataBaseDAOImpl() {
		super();
	}

	public void saveInDataBase(String applicationId, ServiceResponse serviceResponse) {
		this.serviceStatus = new ServiceStatus();
		this.serviceStatus.setApplicationID(applicationId);
		logger.info(this.serviceStatus.getApplicationID());
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
	public List<ServiceResponse> findByDate(BetweenDaysQuery betweenDaysQuery) throws ParseException {
		long firstDate= betweenDaysQuery.getFirstDateEpoch();
		long secondDate= betweenDaysQuery.getSecondDateEpoch();
		String hql = "SELECT e FROM ServiceStatus e WHERE TIME BETWEEN ?1 AND ?2";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class);
		query.setParameter(1, firstDate);
		query.setParameter(2, secondDate);
		return ServiceResponse.setListResponse(query.getResultList());
	}

	public List<ServiceResponse> findByIdAndDate(BetweenDaysAndIdQuery betweenDaysAndIdQuery) throws ParseException {
		String applicationId = betweenDaysAndIdQuery.getApplicationId();
		long firstDate= betweenDaysAndIdQuery.getFirstDateEpoch();
		long secondDate= betweenDaysAndIdQuery.getSecondDateEpoch();
		String hql = "SELECT e FROM ServiceStatus e WHERE APPLICATION_ID= ?1 AND TIME BETWEEN ?2 AND ?3";
		TypedQuery<ServiceStatus> query = entityManager.createQuery(hql, ServiceStatus.class);
		query.setParameter(1, applicationId);
		query.setParameter(2, firstDate);
		query.setParameter(3, secondDate);
		return ServiceResponse.setListResponse(query.getResultList());
	}
}
