package ar.com.learsoft.rest.ws.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.learsoft.rest.ws.model.ServiceStatus;

@Repository
public interface ServiceStatusDataBaseRepository extends JpaRepository<ServiceStatus, Long> {

}
