package trainSimulator.repositories;

import trainSimulator.models.Ticket;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TicketsDao {
    List<Ticket> findAll();

    Ticket get(Long id);

    void saveOrUpdate(Ticket ticket);

    void delete(Long id);

    Ticket findOne(Long id);
}