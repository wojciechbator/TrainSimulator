package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;
import trainSimulator.repositories.PassengersDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service("passengerService")
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

    public void removePassengers() {
        for (Passenger passenger : passengersDaoInterface.findAll()) {
            delete(passenger);
        }
        logger.info("All passengers removed!");
    }
}
