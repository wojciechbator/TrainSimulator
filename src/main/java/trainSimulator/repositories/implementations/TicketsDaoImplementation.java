package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.TicketsDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class TicketsDaoImplementation implements TicketsDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TicketsDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Ticket> findAll() {
        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) sessionFactory.getCurrentSession().
                createCriteria(Ticket.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return tickets;
    }

    @Override
    @Transactional
    public Ticket get(Long id) {
        String hql = "from tickets where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) query.list();
        if (tickets != null && !tickets.isEmpty()) {
            return tickets.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Ticket ticket) {
        sessionFactory.getCurrentSession().saveOrUpdate(ticket);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        sessionFactory.getCurrentSession().delete(ticket);
    }

    @Override
    @Transactional
    public Ticket findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Ticket.class, id);
    }
}
