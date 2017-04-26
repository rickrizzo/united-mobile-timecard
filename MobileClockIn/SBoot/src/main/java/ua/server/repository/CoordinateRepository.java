package ua.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.server.model.Coordinate;

@Repository(value = "CoordinateRepository")
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
