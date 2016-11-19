package repository;

import models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Route findOne(String criteria);
}
