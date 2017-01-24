package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;
import trainSimulator.models.Station;
import trainSimulator.repositories.RoutesDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service("routeService")
@Transactional
public class RouteService {
    private static final Logger logger = Logger.getLogger(RouteService.class);
    private final RoutesDaoInterface routesDaoInterface;

    @Autowired
    public RouteService(RoutesDaoInterface routesDaoInterface) {
        this.routesDaoInterface = routesDaoInterface;
    }

    void saveRoute(final Route route) {
        routesDaoInterface.update(route);
        logger.info("Saved route: " + route.getName());
    }

    List<Route> getAllRoutes() {
        return routesDaoInterface.findAll();
    }

    public void createRoute(final Route route) {
        routesDaoInterface.create(route);
        logger.info("Created route: " + route.getName());
    }

    private void deleteRoute(final Route route) {
        routesDaoInterface.delete(route);
        logger.info("Deleted route: " + route.getName());
    }

    Route findRouteById(final long id) {
        logger.info("Found route with id: " + id);
        return routesDaoInterface.findOne(id);
    }

    public void deleteRouteById(final long id) {
        routesDaoInterface.deleteById(id);
        logger.info("Deleted route by id with id: " + id);
    }

    public void clearRoutes() {
        List<Route> allRoutes = routesDaoInterface.findAll();
        for (Route route : allRoutes) {
            deleteRoute(route);
        }
        logger.info("Cleared routes!");
    }

    public List<Station> getStationsOnRoute(Route route) {
        Route r = routesDaoInterface.findOne(route.getId());
        return r.getStationsOnRoute();
    }
}
