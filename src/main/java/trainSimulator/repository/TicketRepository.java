package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findOne(String criteria);

    List<Ticket> findByPassenger(Passenger passenger);
}
