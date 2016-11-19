package services;

import models.Passenger;
import models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PassengerRepository;
import repository.TicketRepository;

import java.util.List;
import java.util.Random;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final TicketService ticketService;
    private final EventLogService eventLogService;
    private final TicketRepository ticketRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, TicketService ticketService, EventLogService eventLogService,
                            TicketRepository ticketRepository) {
        this.passengerRepository = passengerRepository;
        this.ticketService = ticketService;
        this.eventLogService = eventLogService;
        this.ticketRepository = ticketRepository;
    }

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger findByName(String name) {
        return passengerRepository.findOne(name);
    }

    public Passenger findByID(int id) {
        return passengerRepository.findOne(String.valueOf(id));
    }

    public void save(Passenger passenger) {
        //more things to set for passenger when saving, etc: give him some ticket, some params
        //passenger.setTickets();
        passengerRepository.save(passenger);
    }

    public void delete(int id) {
        passengerRepository.delete(id);
    }

    public void buyTicket(Passenger passenger, Ticket ticket) {
        Random random = new Random();
        ticket.setPassenger(passenger);
        ticket.setPrice(random.nextInt(30) + 10);
        ticketService.saveTicket(ticket);
    }

    public void removePassengers() {
        for (Passenger passenger : passengerRepository.findAll()) {
            delete(passenger.getId());
        }
    }
}
