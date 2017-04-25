package ua.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="location")
public class Location implements IPersistable<Long> {
	
    @Id @GeneratedValue
    @Column (name="location_ID")
    private Long locationID;

    @Column(name = "location_UUID")
    private String locationUUID;

    @Column(name = "location_name")
    private String locationName;
    
    @Column(name = "location_latitude")
    private Double latitude;

    @Column(name = "location_longitude")
    private Double longitude;
    
    public Location() {
    	
    }

    public Location(String uuid, Double latitude, Double longitude) {
    	this.locationUUID = uuid;
    	this.latitude = latitude;
    	this.longitude = longitude;
    }
    
	@Override
	public Long getId() {
		return locationID;
	}

	public String getLocationUUID() {
		return locationUUID;
	}

	public void setLocationUUID(String locationUUID) {
		this.locationUUID = locationUUID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
