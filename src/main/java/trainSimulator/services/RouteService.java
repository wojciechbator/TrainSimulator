package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;
import trainSimulator.repositories.RoutesDao;
import trainSimulator.repositories.implementations.RoutesDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class RouteService {
    private final RoutesDao routesDao;

    @Autowired
    public RouteService(RoutesDaoImplementation routesDao) {
        this.routesDao = routesDao;
    }

    public void saveRoute(Route route) {
        routesDao.saveOrUpdate(route);
    }

    public void deleteRoute(Long id) {
        routesDao.delete(id);
    }

    public void findRoute(Long id) {
        routesDao.findOne(id);
    }

    public void clearRoutes() {
        List<Route> allRoutes = routesDao.findAll();
        for (Route route : allRoutes) {
            deleteRoute(route.getId());
        }
    }


}
