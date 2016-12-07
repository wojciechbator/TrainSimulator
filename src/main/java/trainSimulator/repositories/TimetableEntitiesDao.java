package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.TimetableEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TimetableEntitiesDao extends AbstractJpaDao<TimetableEntity> implements TimetableEntitiesDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;
    public TimetableEntitiesDao() {
        super();
        setClazz(TimetableEntity.class);
    }

    @Override
    @Cacheable("timetableEntity")
    public TimetableEntity findOne(final long id) {
        return entityManager.find(TimetableEntity.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable("timetableEntities")
    public List<TimetableEntity> findAll() {
        return entityManager.createQuery("from " + TimetableEntity.class.getName()).getResultList();
    }
}
