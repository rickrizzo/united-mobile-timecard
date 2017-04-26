package ua.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.server.model.Coordinate;
import ua.server.model.Location;
import ua.server.repository.CoordinateRepository;
import ua.server.repository.LocationRepository;

@Service
public class LocationService extends AbstractService<Location, Long> {

	@Autowired
	private CoordinateRepository coordinateRepository;

	@SuppressWarnings("unused")
	private LocationRepository locationRepository;

	@Autowired
	public LocationService(LocationRepository locationRepository) {
		super(locationRepository);
		this.locationRepository = locationRepository;
	}

	public boolean validateCoordinate(Location location) {
		Coordinate coordinate = coordinateRepository.findOne(1l);
		
		Double northLat = coordinate.getNorthLatitude();
		Double southLat = coordinate.getSouthLatitude();
		Double westLong = coordinate.getWestLongtitude();
		Double eastLong = coordinate.getEastLongtitude();

		Double currLat = location.getLatitude();
		Double currLong = location.getLongitude();

		if (currLat < northLat && currLat > southLat && currLong > westLong && currLong < eastLong)
			return true;
		else
			return false;
	}

}
