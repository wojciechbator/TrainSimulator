package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.TicketsDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TicketService {
    private final TicketsDaoInterface ticketsDaoInterface;

    @Autowired
    public TicketService(TicketsDaoInterface ticketsDao) {
        this.ticketsDaoInterface = ticketsDao;
    }

    public void saveTicket(final Ticket ticket) {
        ticketsDaoInterface.update(ticket);
    }

    public void deleteTicket(final Ticket ticket) {
        ticketsDaoInterface.delete(ticket);
    }

    public void deleteTicketById(final long id) {
        ticketsDaoInterface.deleteById(id);
    }

    public void createTicket(final Ticket ticket) {
        ticketsDaoInterface.create(ticket);
    }

    public List<Ticket> findAllTickets() {
        return ticketsDaoInterface.findAll();
    }

    public Ticket findTicket(final long id) {
        return ticketsDaoInterface.findOne(id);
    }

    public void clearTickets() {
        List<Ticket> allTickets = ticketsDaoInterface.findAll();
        for (Ticket ticket : allTickets) {
            deleteTicket(ticket);
        }
    }
}
