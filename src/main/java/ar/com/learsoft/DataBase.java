package ar.com.learsoft;

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
@Table(name= "AFIP_CHECKER")
public class DataBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID;
    @Column (name ="ID_CLIENTE")
    private int idCliente;

    @Column (name = "TIME")
    private Long time;
    @Column (name ="SERVICE_STATUS")
    private String status;

    public DataBase() {
        super();
    }


    public void saveData(Client client, ServiceResponse serviceResponse) {
        this.setID(client.getId());
        this.setStatus(serviceResponse.getStatus());
        this.setTime(serviceResponse.getTimestamp());
    }
}