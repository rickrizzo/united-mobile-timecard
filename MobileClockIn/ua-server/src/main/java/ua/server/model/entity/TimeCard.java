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
import org.hibernate.validator.Length;

@Entity
@Proxy(lazy = true)
@Table(name = "timeCard")
public class TimeCard implements Serializable, IEntity {

	public TimeCard() {
	}

	@Column(name = "timeCardId", nullable = false, length = 11)
	@Id
	@GeneratedValue(generator = "UA_SERVER_MODEL_ENTITY_TIMECARD_ID_GENERATOR")
	@GenericGenerator(name = "UA_SERVER_MODEL_ENTITY_TIMECARD_ID_GENERATOR", strategy = "increment")
	private int timeCardId;

	@Column(name="uuid", nullable=false, length=16)	
	private byte[] uuid;	
	
	@Column(name = "clockingIn", nullable = false, length = 1)
	private boolean clockingIn;

	@Column(name = "location", nullable = false, length = 50)
	@Length(max = 50)
	private String location;

	@Column(name = "assignmentId", nullable = false, length = 11)
	private int assignmentId;

	@Column(name = "isLate", nullable = true, length = 1)
	private boolean isLate;

	@Column(name = "reason", nullable = false, length = 50)
	@Length(max = 50)
	private String reason;

	@ManyToOne(targetEntity = ua.server.model.entity.Employee.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", nullable = false) })
	private ua.server.model.entity.Employee employee;

	@Transient
	private static final long serialVersionUID = 1L;
	
	public int getTimeCardId() {
		return timeCardId;
	}

	public void setTimeCardId(int timeCardId) {
		this.timeCardId = timeCardId;
	}

	public boolean isClockingIn() {
		return clockingIn;
	}

	public void setClockingIn(boolean clockingIn) {
		this.clockingIn = clockingIn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public boolean isLate() {
		return isLate;
	}

	public void setLate(boolean isLate) {
		this.isLate = isLate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
