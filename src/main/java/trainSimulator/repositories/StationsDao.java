package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Station;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class StationsDao extends AbstractJpaDao<Station> implements StationsDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public StationsDao() {
        super();
        setClazz(Station.class);
    }
}
