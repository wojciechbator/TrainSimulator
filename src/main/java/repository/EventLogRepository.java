package repository;

import models.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
    EventLog findEvent(Integer id);
}
