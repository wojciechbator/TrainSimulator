package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class TicketsDao extends AbstractJpaDao<Ticket> implements TicketsDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public TicketsDao() {
        super();
        setClazz(Ticket.class);
    }

}
