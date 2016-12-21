package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.PassengersDaoInterface;

import java.util.List;
import java.util.Random;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class PassengerService {
    private static final Logger logger = Logger.getLogger(PassengerService.class);
    private final PassengersDaoInterface passengersDaoInterface;
    private final TicketService ticketService;

    @Autowired
    public PassengerService(PassengersDaoInterface passengersDaoInterface, TicketService ticketService) {
        this.passengersDaoInterface = passengersDaoInterface;
        this.ticketService = ticketService;
    }

    public List<Passenger> findAll() {
        return passengersDaoInterface.findAll();
    }

    public Passenger findById(final long id) {
        return passengersDaoInterface.findOne(id);
    }

    public void createPassenger(final Passenger passenger) {
        passengersDaoInterface.create(passenger);
        logger.info("Created passenger with id: " + passenger.getId());
    }

    public void deletePassengerById(final long id) {
        passengersDaoInterface.deleteById(id);
    }

    public void save(final Passenger passenger) {
        //more things to set for passenger when saving, etc: give him some ticket, some params
        //passenger.setTickets();
        passengersDaoInterface.update(passenger);
        logger.info("Saved passenger with id: " + passenger.getId());
    }

    public void delete(final Passenger passenger) {
        passengersDaoInterface.delete(passenger);
        logger.info("Passenger with id: " + passenger.getId() + " deleted!");
    }

    public void buyTicket(Passenger passenger, Ticket ticket) {
        Random random = new Random();
        ticket.setPassenger(passenger);
        ticket.setPrice(random.nextInt(30) + 10);
        ticketService.saveTicket(ticket);
        logger.info("Passenger with id: " + passenger.getId() + " has bought a ticket with id: " + ticket.getId());
    }

    public void removePassengers() {
        for (Passenger passenger : passengersDaoInterface.findAll()) {
            delete(passenger);
        }
        logger.info("All passengers removed!");
    }
}
