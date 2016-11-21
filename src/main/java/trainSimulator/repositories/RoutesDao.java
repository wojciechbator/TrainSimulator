package trainSimulator.repositories;

import trainSimulator.models.Route;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */

public interface RoutesDao {
    List<Route> findAll();

    Route get(Long id);

    void saveOrUpdate(Route route);

    void delete(Long id);

    Route findOne(Long id);
}

