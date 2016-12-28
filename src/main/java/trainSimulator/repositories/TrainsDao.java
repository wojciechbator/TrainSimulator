package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TrainsDao extends AbstractJpaDao<Train> implements TrainsDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;
    public TrainsDao() {
        super();
        setClazz(Train.class);
    }
}
