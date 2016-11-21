package trainSimulator.repositories;

import trainSimulator.models.EventLog;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface EventLogsDao {
    List<EventLog> findAll();

    EventLog get(Long id);

    void saveOrUpdate(EventLog eventLog);

    void delete(Long id);

    EventLog findOne(Long id);
}
