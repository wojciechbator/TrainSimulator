package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;
import trainSimulator.repositories.PassengersDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class PassengersDaoImplementation implements PassengersDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PassengersDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Passenger> findAll() {
        @SuppressWarnings("unchecked")
        List<Passenger> passengers = (List<Passenger>) sessionFactory.getCurrentSession()
                .createCriteria(Passenger.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return passengers;
    }

    @Override
    @Transactional
    public Passenger get(Long id) {
        String hql = "from passengers where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Passenger> passengers = (List<Passenger>) query.list();

        if (passengers != null && !passengers.isEmpty()) {
            return passengers.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Passenger passenger) {
        sessionFactory.getCurrentSession().saveOrUpdate(passenger);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Passenger passengerToDelete = new Passenger();
        passengerToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(passengerToDelete);
    }

    @Override
    @Transactional
    public Passenger findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Passenger.class, id);
    }
}
