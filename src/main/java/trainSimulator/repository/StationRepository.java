package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Route;
import trainSimulator.models.Station;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findOne(String criteria);

    List<Station> findByRoute(Route route);
}
