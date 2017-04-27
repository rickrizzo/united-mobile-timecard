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
@Table(name = "location")
public class Location implements Serializable, IEntity {
	
	public Location() {
		
	}

	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name = "locationId", nullable = false, length = 11)
	@Id
	@GeneratedValue(generator = "UA_SERVER_MODEL_ENTITY_LOCATION_ID_GENERATOR")
	@GenericGenerator(name = "UA_SERVER_MODEL_ENTITY_LOCATION_ID_GENERATOR", strategy = "increment")
	private int locationId;
	
	@Column(name="uuid", nullable=false, length=16)	
	private String uuid;	
	
	@Column(name = "locationName", nullable = false, length = 50)
	@Length(max = 50)
	private String locationName;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}