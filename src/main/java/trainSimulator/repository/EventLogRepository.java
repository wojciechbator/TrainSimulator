package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.EventLog;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
    EventLog findEvent(Integer id);
}
