package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.Ticket;
import trainSimulator.repository.TicketRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository TicketRepository) {
        this.ticketRepository = TicketRepository;
    }

    public void saveTicket(Ticket Ticket) {
        ticketRepository.save(Ticket);
    }

    public void deleteTicket(Ticket Ticket) {
        ticketRepository.delete(Ticket);
    }

    public void findTicket(Integer id) {
        ticketRepository.findOne(String.valueOf(id));
    }

    public void clearTickets() {
        List<Ticket> allTickets = ticketRepository.findAll();
        allTickets.forEach(this::deleteTicket);
    }
}
