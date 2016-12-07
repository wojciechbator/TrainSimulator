package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;
import trainSimulator.repositories.RoutesDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class RouteService {
    private final RoutesDaoInterface routesDaoInterface;

    @Autowired
    public RouteService(RoutesDaoInterface routesDaoInterface) {
        this.routesDaoInterface = routesDaoInterface;
    }

    void saveRoute(final Route route) {
        routesDaoInterface.update(route);
    }

    List<Route> getAllRoutes() {
        return routesDaoInterface.findAll();
    }

    public void createRoute(final Route route) {
        routesDaoInterface.create(route);
    }

    private void deleteRoute(final Route route) {
        routesDaoInterface.delete(route);
    }

    Route findRouteById(final long id) {
        return routesDaoInterface.findOne(id);
    }

    public void deleteRouteById(final long id) {
        routesDaoInterface.deleteById(id);
    }

    public void clearRoutes() {
        List<Route> allRoutes = routesDaoInterface.findAll();
        for (Route route : allRoutes) {
            deleteRoute(route);
        }
    }
}
