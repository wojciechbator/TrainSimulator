package repository;

import models.Route;
import models.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findOne(String criteria);

    List<Station> findByRoute(Route route);
}
