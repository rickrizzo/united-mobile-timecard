package ua.server.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = true)
@Table(name = "device")
public class Device implements Serializable {

	public Device() {
	}

	@Column(name = "deviceId", nullable = false, length = 11)
	@Id
	@GeneratedValue(generator = "UA_SERVER_MODEL_ENTITY_DEVICE_ID_GENERATOR")
	@GenericGenerator(name = "UA_SERVER_MODEL_ENTITY_DEVICE_ID_GENERATOR", strategy = "increment")
	private int deviceId;

	@Column(name="uuid", nullable=false, length=16)	
	private byte[] uuid;	
	
	@ManyToOne(targetEntity = ua.server.model.entity.Employee.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", nullable = false) })
	private ua.server.model.entity.Employee employee;

	@Transient
	private static final long serialVersionUID = 1L;

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public ua.server.model.entity.Employee getEmployee() {
		return employee;
	}

	public void setEmployee(ua.server.model.entity.Employee employee) {
		this.employee = employee;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
