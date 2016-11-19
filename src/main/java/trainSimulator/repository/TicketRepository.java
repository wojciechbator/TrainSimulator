package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;

import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findOne(String criteria);

    Set<Ticket> findByPassenger(Passenger passenger);
}
