package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Ticket;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TicketsDao extends AbstractJpaDao<Ticket> implements TicketsDaoInterface {
    public TicketsDao() {
        super();
        setClazz(Ticket.class);
    }
}
