package trainSimulator.repositories;

import trainSimulator.models.Route;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */

public interface RoutesDaoInterface {
    List<Route> findAll();

    void create(Route route);

    Route update(Route route);

    void delete(Route route);

    Route findOne(long id);

    void deleteById(long entityId);
}

