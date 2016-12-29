package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Station;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class StationsDao extends AbstractJpaDao<Station> implements StationsDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public StationsDao() {
        super();
        setClazz(Station.class);
    }
}
