package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class RoutesDao extends AbstractJpaDao<Route> implements RoutesDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;
    public RoutesDao() {
        super();
        setClazz(Route.class);
    }
}
