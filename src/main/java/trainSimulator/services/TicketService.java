package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Ticket;
import trainSimulator.models.User;
import trainSimulator.repositories.TicketsDaoInterface;
import trainSimulator.repositories.UserDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service("ticketService")
@Transactional
public class TicketService {
    private static final Logger logger = Logger.getLogger(TicketService.class);
    private final TicketsDaoInterface ticketsDaoInterface;
    private final UserDaoInterface userDaoInterface;

    @Autowired
    public TicketService(TicketsDaoInterface ticketsDao, UserDaoInterface userDaoInterface) {
        this.ticketsDaoInterface = ticketsDao;
        this.userDaoInterface = userDaoInterface;
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

    public void createTicket(final Ticket ticket, String name) {
        User user = userDaoInterface.findOneByName(name);
        ticket.setUser(user);
        ticketsDaoInterface.update(ticket);
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

    public void setOwnerForTicket(User owner, Ticket ticket) {
        ticket.setUser(owner);
    }

    public User getOwnerOfTicket(Ticket ticket) {
        return ticket.getUser();
    }
}
