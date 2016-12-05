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

    public void saveRoute(final Route route) {
        routesDaoInterface.update(route);
    }

    public List<Route> getAllRoutes() {
        return routesDaoInterface.findAll();
    }

    public void createRoute(final Route route) {
        routesDaoInterface.create(route);
    }

    public void deleteRoute(final Route route) {
        routesDaoInterface.delete(route);
    }

    public Route findRouteById(final long id) {
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

//    public void addStationToRoute(final Station station, final long id) {
//        Route route = routesDaoInterface.findOne(id);
//        List<Station> stationsOnRoute = new ArrayList<>();
//        stationsOnRoute.add(station);
//        route.setStationsOnRoute(stationsOnRoute);
//    }


}
