package ar.com.learsoft.rest.ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "AFIP_CHECKER")
public class ServiceStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ID;
	@Column(name = "APPLICATION_ID")
	private String applicationID;
	@Column(name = "TIME")
	private Long time;
	@Column(name = "SERVICE_STATUS")
	private String status;

	public ServiceStatus() {
		super();
	}
}
