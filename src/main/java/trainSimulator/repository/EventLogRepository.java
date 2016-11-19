package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.EventLog;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
    EventLog findEvent(Integer id);
}
