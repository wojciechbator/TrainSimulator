package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.TimetableEntity;
import trainSimulator.repositories.TimetableEntitiesDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class TimetableEntitiesDaoImplementation implements TimetableEntitiesDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TimetableEntitiesDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<TimetableEntity> findAll() {
        @SuppressWarnings("unchecked")
        List<TimetableEntity> timetableEntities = (List<TimetableEntity>) sessionFactory.getCurrentSession().
                createCriteria(TimetableEntity.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return timetableEntities;
    }

    @Override
    @Transactional
    public TimetableEntity get(Long id) {
        String hql = "from timetable where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<TimetableEntity> timetableEntities = (List<TimetableEntity>) query.list();
        if (timetableEntities != null && !timetableEntities.isEmpty()) {
            return timetableEntities.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(List<TimetableEntity> timetableEntities) {
        sessionFactory.getCurrentSession().saveOrUpdate(timetableEntities);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TimetableEntity timetableEntityToDelete = new TimetableEntity();
        timetableEntityToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(timetableEntityToDelete);
    }

    @Override
    @Transactional
    public TimetableEntity findOne(Long id) {
        return sessionFactory.getCurrentSession().get(TimetableEntity.class, id);
    }
}
