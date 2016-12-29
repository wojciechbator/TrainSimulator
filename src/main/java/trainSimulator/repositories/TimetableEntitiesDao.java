package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.TimetableEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class TimetableEntitiesDao extends AbstractJpaDao<TimetableEntity> implements TimetableEntitiesDaoInterface {
    @PersistenceContext
    EntityManager entityManager;

    public TimetableEntitiesDao() {
        super();
        setClazz(TimetableEntity.class);
    }
}
