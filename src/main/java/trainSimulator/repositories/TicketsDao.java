package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TicketsDao extends AbstractJpaDao<Ticket> implements TicketsDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;
    public TicketsDao() {
        super();
        setClazz(Ticket.class);
    }

    @Override
    @Cacheable("ticket")
    public Ticket findOne(final long id) {
        return entityManager.find(Ticket.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable("tickets")
    public List<Ticket> findAll() {
        return entityManager.createQuery("from " + Ticket.class.getName()).getResultList();
    }
}
