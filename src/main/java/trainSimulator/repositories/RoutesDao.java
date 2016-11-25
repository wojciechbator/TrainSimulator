package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Route;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class RoutesDao extends AbstractJpaDao<Route> implements RoutesDaoInterface {
    public RoutesDao() {
        super();
        setClazz(Route.class);
    }
}
