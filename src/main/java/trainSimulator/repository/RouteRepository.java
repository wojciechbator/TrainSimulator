package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.Route;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Route findOne(String criteria);
}
