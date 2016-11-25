package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.PassengersDaoInterface;
import trainSimulator.repositories.TicketsDaoInterface;

import java.util.List;
import java.util.Random;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class PassengerService {
    private final PassengersDaoInterface passengersDaoInterface;
    private final TicketService ticketService;
    private final EventLogService eventLogService;
    private final TicketsDaoInterface ticketsDaoInterface;

    @Autowired
    public PassengerService(PassengersDaoInterface passengersDaoInterface, TicketService ticketService, EventLogService eventLogService,
                            TicketsDaoInterface ticketsDaoInterface) {
        this.passengersDaoInterface = passengersDaoInterface;
        this.ticketService = ticketService;
        this.eventLogService = eventLogService;
        this.ticketsDaoInterface = ticketsDaoInterface;
    }

    public List<Passenger> findAll() {
        return passengersDaoInterface.findAll();
    }

    public Passenger findById(final long id) {
        return passengersDaoInterface.findOne(id);
    }

    public void createPassenger(final Passenger passenger) {
        passengersDaoInterface.create(passenger);
    }

    public void deletePassengerById(final long id) {
        passengersDaoInterface.deleteById(id);
    }

    public void save(final Passenger passenger) {
        //more things to set for passenger when saving, etc: give him some ticket, some params
        //passenger.setTickets();
        passengersDaoInterface.update(passenger);
    }

    public void delete(final Passenger passenger) {
        passengersDaoInterface.delete(passenger);
    }

    public void buyTicket(Passenger passenger, Ticket ticket) {
        Random random = new Random();
        ticket.setPassenger(passenger);
        ticket.setPrice(random.nextInt(30) + 10);
        ticketService.saveTicket(ticket);
    }

    public void removePassengers() {
        for (Passenger passenger : passengersDaoInterface.findAll()) {
            delete(passenger);
        }
    }
}
