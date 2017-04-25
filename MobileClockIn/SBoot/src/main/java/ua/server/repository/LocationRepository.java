package ua.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.server.model.Location;

@Repository(value = "LocationRepository")
public interface LocationRepository extends JpaRepository<Location, Long> {
}
