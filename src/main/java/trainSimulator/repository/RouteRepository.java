package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Route;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Route findOne(String criteria);
}
