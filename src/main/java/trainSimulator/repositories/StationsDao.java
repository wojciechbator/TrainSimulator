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
    private EntityManager entityManager;
    public StationsDao() {
        super();
        setClazz(Station.class);
    }

    @Override
    @Cacheable("station")
    public Station findOne(final long id) {
        return entityManager.find(Station.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable("stations")
    public List<Station> findAll() {
        return entityManager.createQuery("from " + Station.class.getName()).getResultList();
    }
}
