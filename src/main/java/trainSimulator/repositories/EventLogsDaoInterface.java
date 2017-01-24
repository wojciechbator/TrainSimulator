package trainSimulator.repositories;

import trainSimulator.models.EventLog;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface EventLogsDaoInterface {
    List<EventLog> findAll();

    void create(EventLog eventLog);

    EventLog update(EventLog eventLog);

    EventLog findOne(long id);

    void delete(EventLog eventLog);

    void deleteById(long entityId);
}
