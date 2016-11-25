package trainSimulator.repositories;

import trainSimulator.models.Ticket;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TicketsDaoInterface {
    List<Ticket> findAll();

    void create(Ticket ticket);

    Ticket update(Ticket ticket);

    void delete(Ticket ticket);

    Ticket findOne(long id);

    void deleteById(long entityId);
}