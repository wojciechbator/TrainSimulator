package services;

import models.EventLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EventLogRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
class EventLogService {
    private final EventLogRepository eventLogRepository;

    @Autowired
    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    void saveEvent(EventLog eventLog) {
        eventLogRepository.save(eventLog);
    }

    public EventLog findEvent(Integer id) {
        return eventLogRepository.findEvent(id);
    }

    private void deleteEventLog(EventLog eventLog) {
        eventLogRepository.delete(eventLog);
    }

    void clearEvents() {
        List<EventLog> eventLogs = eventLogRepository.findAll();
        eventLogs.forEach(this::deleteEventLog);
    }
}
