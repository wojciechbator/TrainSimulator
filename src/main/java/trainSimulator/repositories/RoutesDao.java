package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class RoutesDao extends AbstractJpaDao<Route> implements RoutesDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public RoutesDao() {
        super();
        setClazz(Route.class);
    }
}
