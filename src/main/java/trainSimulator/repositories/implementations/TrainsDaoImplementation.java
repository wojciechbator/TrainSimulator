package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Train;
import trainSimulator.repositories.TrainsDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class TrainsDaoImplementation implements TrainsDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TrainsDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Train> findAll() {
        @SuppressWarnings("unchecked")
        List<Train> trains = (List<Train>) sessionFactory.getCurrentSession().createCriteria(Train.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return trains;
    }

    @Override
    @Transactional
    public Train get(Long id) {
        String hql = "from trains where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Train> trains = (List<Train>) sessionFactory.getCurrentSession()
                .createCriteria(Train.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (trains != null && !trains.isEmpty()) {
            return trains.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Train train) {
        sessionFactory.getCurrentSession().saveOrUpdate(train);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Train train = new Train();
        train.setId(id);
        sessionFactory.getCurrentSession().delete(train);
    }

    @Override
    @Transactional
    public Train findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Train.class, id);
    }
}
