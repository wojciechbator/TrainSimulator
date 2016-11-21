package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Station;
import trainSimulator.repositories.StationsDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class StationsDaoImplementation implements StationsDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public StationsDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Station> findAll() {
        @SuppressWarnings("unchecked")
        List<Station> stations = (List<Station>) sessionFactory.getCurrentSession()
                .createCriteria(Station.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return stations;
    }

    @Override
    @Transactional
    public Station get(Long id) {
        String hql = "from stations where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Station> stations = (List<Station>) query.list();

        if (stations != null && !stations.isEmpty()) {
            return stations.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Station station) {
        sessionFactory.getCurrentSession().saveOrUpdate(station);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Station stationToDelete = new Station();
        stationToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(stationToDelete);
    }

    @Override
    @Transactional
    public Station findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Station.class, id);
    }
}
