package ua.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

import ua.server.model.abstr.IEntity;

@Entity
@Proxy(lazy = true)
@Table(name = "coordinate")
public class Coordinate implements IEntity<Long> {

	@Id
	@GeneratedValue
	@Column(name = "coordinate_ID")
	private Long coordinateID;

	@Column(name = "north_lat")
	private Double northLatitude;

	@Column(name = "south_lat")
	private Double southLatitude;

	@Column(name = "west_long")
	private Double westLongtitude;

	@Column(name = "east_long")
	private Double eastLongtitude;

	public Coordinate() {

	}
	
	public Coordinate(Double northLat, Double southLat, Double westLong, Double eastLong) {
		this.northLatitude = northLat;
		this.southLatitude = southLat;
		this.westLongtitude = westLong;
		this.eastLongtitude = eastLong;
	}

	public Long getId() {
		return coordinateID;
	}

	public Double getNorthLatitude() {
		return northLatitude;
	}

	public void setNorthLatitude(Double northLatitude) {
		this.northLatitude = northLatitude;
	}

	public Double getSouthLatitude() {
		return southLatitude;
	}

	public void setSouthLatitude(Double southLatitude) {
		this.southLatitude = southLatitude;
	}

	public Double getWestLongtitude() {
		return westLongtitude;
	}

	public void setWestLongtitude(Double westLongtitude) {
		this.westLongtitude = westLongtitude;
	}

	public Double getEastLongtitude() {
		return eastLongtitude;
	}

	public void setEastLongtitude(Double eastLongtitude) {
		this.eastLongtitude = eastLongtitude;
	}

}
