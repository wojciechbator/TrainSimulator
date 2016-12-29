package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private static final Logger logger = Logger.getLogger(TicketService.class);
    private final TicketsDaoInterface ticketsDaoInterface;

    @Autowired
    public TicketService(TicketsDaoInterface ticketsDao) {
        this.ticketsDaoInterface = ticketsDao;
    }

    void saveTicket(final Ticket ticket) {
        ticketsDaoInterface.update(ticket);
        logger.info("Saved ticket with id: " + ticket.getId());
    }

    private void deleteTicket(final Ticket ticket) {
        ticketsDaoInterface.delete(ticket);
        logger.info("Deleted ticket with id: " + ticket.getId());
    }

    public void deleteTicketById(final long id) {
        ticketsDaoInterface.deleteById(id);
        logger.info("Deleted ticket by id with id: " + id);
    }

    public void createTicket(final Ticket ticket) {
        ticketsDaoInterface.create(ticket);
        logger.info("Created ticket with id: " + ticket.getId());
    }

    public List<Ticket> findAllTickets() {
        return ticketsDaoInterface.findAll();
    }

    public Ticket findTicket(final long id) {
        logger.info("Found ticket with id: " + id);
        return ticketsDaoInterface.findOne(id);
    }

    public void clearTickets() {
        List<Ticket> allTickets = ticketsDaoInterface.findAll();
        for (Ticket ticket : allTickets) {
            deleteTicket(ticket);
        }
        logger.info("Cleared all tickets!");
    }
}
