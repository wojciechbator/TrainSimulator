package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.Route;
import trainSimulator.repository.RouteRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public void saveRoute(Route route) {
        routeRepository.save(route);
    }

    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }

    public void findRoute(Integer id) {
        routeRepository.findOne(String.valueOf(id));
    }

    public void clearRoutes() {
        List<Route> allRoutes = routeRepository.findAll();
        allRoutes.forEach(this::deleteRoute);
    }


}
