package ua.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.server.model.Location;
import ua.server.repository.LocationRepository;

@Service
public class LocationService extends AbstractService<Location, Long> {

    @SuppressWarnings("unused")
	private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        super(locationRepository);
        this.locationRepository=locationRepository;
    }
    
    public boolean validateCoordinate(Location entity) {
    	Double northLat = 42.737029;
    	Double southLat = 42.735228;
    	Double westLong = -73.685241;
    	Double eastLong = -73.678922;
    	
    	Double currLat = entity.getLatitude();
    	Double currLong = entity.getLongitude();
    	
    	if(currLat < northLat && currLat > southLat && currLong > westLong && currLong < eastLong)
    		return true;
    	else return false;
    }

}
