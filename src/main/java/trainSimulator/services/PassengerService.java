package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;
import trainSimulator.repositories.PassengersDao;
import trainSimulator.repositories.TicketsDao;
import trainSimulator.repositories.implementations.PassengersDaoImplementation;
import trainSimulator.repositories.implementations.TicketsDaoImplementation;

import java.util.List;
import java.util.Random;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
@Transactional
public class PassengerService {
    private final PassengersDao passengersDao;
    private final TicketService ticketService;
    private final EventLogService eventLogService;
    private final TicketsDao ticketsDao;

    @Autowired
    public PassengerService(PassengersDaoImplementation passengersDaoImplementation, TicketService ticketService, EventLogService eventLogService,
                            TicketsDaoImplementation ticketsDaoImplementation) {
        this.passengersDao = passengersDaoImplementation;
        this.ticketService = ticketService;
        this.eventLogService = eventLogService;
        this.ticketsDao = ticketsDaoImplementation;
    }

    public List<Passenger> findAll() {
        return passengersDao.findAll();
    }

    public Passenger findByID(Long id) {
        return passengersDao.findOne(id);
    }

    public void save(Passenger passenger) {
        //more things to set for passenger when saving, etc: give him some ticket, some params
        //passenger.setTickets();
        passengersDao.saveOrUpdate(passenger);
    }

    public void delete(Long id) {
        passengersDao.delete(id);
    }

    public void buyTicket(Passenger passenger, Ticket ticket) {
        Random random = new Random();
        ticket.setPassenger(passenger);
        ticket.setPrice(random.nextInt(30) + 10);
        ticketService.saveTicket(ticket);
    }

    public void removePassengers() {
        for (Passenger passenger : passengersDao.findAll()) {
            delete(passenger.getId());
        }
    }
}
