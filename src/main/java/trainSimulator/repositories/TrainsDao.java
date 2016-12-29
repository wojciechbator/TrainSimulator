package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class TrainsDao extends AbstractJpaDao<Train> implements TrainsDaoInterface {
    @PersistenceContext
    EntityManager entityManager;

    public TrainsDao() {
        super();
        setClazz(Train.class);
    }
}
