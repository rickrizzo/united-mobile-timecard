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

@Entity
@Proxy(lazy = true)
@Table(name = "coordinate")
public class Coordinate implements Serializable, IEntity {
	
	public Coordinate() {
		
	}
	
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Column(name = "coordinateId", nullable = false, length = 11)
	@Id
	@GeneratedValue(generator = "UA_SERVER_MODEL_ENTITY_COORDINATE_ID_GENERATOR")
	@GenericGenerator(name = "UA_SERVER_MODEL_ENTITY_COORDINATE_ID_GENERATOR", strategy = "increment")
	private int coordinateId;
	
	@Column(name="uuid", nullable=false, length=16)	
	private String uuid;	

	@Column(name = "northCoordinate", nullable = false)
	private long northCoordinate;
	
	@Column(name = "southCoordinate", nullable = false)
	private long southCoordinate;

	@Column(name = "westCoordinate", nullable = false)
	private long westCoordinate;
	
	@Column(name = "eastCoordinate", nullable = false)
	private long eastCoordinate;

	public int getCoordinateId() {
		return coordinateId;
	}

	public void setCoordinateId(int coordinateId) {
		this.coordinateId = coordinateId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getNorthCoordinate() {
		return northCoordinate;
	}

	public void setNorthCoordinate(long northCoordinate) {
		this.northCoordinate = northCoordinate;
	}

	public long getSouthCoordinate() {
		return southCoordinate;
	}

	public void setSouthCoordinate(long southCoordinate) {
		this.southCoordinate = southCoordinate;
	}

	public long getWestCoordinate() {
		return westCoordinate;
	}

	public void setWestCoordinate(long westCoordinate) {
		this.westCoordinate = westCoordinate;
	}

	public long getEastCoordinate() {
		return eastCoordinate;
	}

	public void setEastCoordinate(long eastCoordinate) {
		this.eastCoordinate = eastCoordinate;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
