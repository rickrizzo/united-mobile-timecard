package ua.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ua.server.model.Coordinate;
import ua.server.repository.CoordinateRepository;

@Service
public class CoordinateService extends AbstractService<Coordinate, Long> {

	private CoordinateRepository coordinateRepository;

	@Autowired
	public CoordinateService(CoordinateRepository coordinateRepository) {
		super(coordinateRepository);
		this.coordinateRepository = coordinateRepository;
	}
	
	@Bean
	public Coordinate insertValues() {
		Double northLat = 42.737029;
		Double southLat = 42.727416;
		Double westLong = -73.685241;
		Double eastLong = -73.678922;
		
		Coordinate entity = new Coordinate(northLat, southLat, westLong, eastLong);
		return coordinateRepository.save(entity);
	}
}
