package ua.server.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.Length;

@Entity
@Proxy(lazy = true)
@Table(name = "employee")
public class Employee implements Serializable, IEntity {

	public Employee() {
	}

	@Column(name = "employeeId", nullable = false, length = 11)
	@Id
	@GeneratedValue(generator = "UA_SERVER_MODEL_ENTITY_EMPLOYEE_ID_GENERATOR")
	@GenericGenerator(name = "UA_SERVER_MODEL_ENTITY_EMPLOYEE_ID_GENERATOR", strategy = "increment")
	private int employeeId;

	@Column(name="uuid", nullable=false, length=16)	
	private byte[] uuid;	
	
	@Column(name = "firstName", nullable = false, length = 50)
	@Length(max = 50)
	private String firstName;

	@Column(name = "lastName", nullable = false, length = 50)
	@Length(max = 50)
	private String lastName;

	@Transient
	private static final long serialVersionUID = 1L;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
