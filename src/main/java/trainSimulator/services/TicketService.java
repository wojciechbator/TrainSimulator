package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.TicketsDao;
import trainSimulator.repositories.implementations.TicketsDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TicketService {
    private final TicketsDao ticketsDao;

    @Autowired
    public TicketService(TicketsDaoImplementation ticketsDao) {
        this.ticketsDao = ticketsDao;
    }

    public void saveTicket(Ticket Ticket) {
        ticketsDao.saveOrUpdate(Ticket);
    }

    public void deleteTicket(Long id) {
        ticketsDao.delete(id);
    }

    public void findTicket(Long id) {
        ticketsDao.findOne(id);
    }

    public void clearTickets() {
        List<Ticket> allTickets = ticketsDao.findAll();
        for (Ticket ticket : allTickets) {
            deleteTicket(ticket.getId());
        }
    }
}
