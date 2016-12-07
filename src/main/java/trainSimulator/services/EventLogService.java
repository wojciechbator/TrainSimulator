package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;
import trainSimulator.repositories.EventLogsDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class EventLogService {
    private final EventLogsDaoInterface eventLogsDao;

    @Autowired
    public EventLogService(EventLogsDaoInterface eventLogsDao) {
        this.eventLogsDao = eventLogsDao;
    }

    void saveEvent(final EventLog eventLog) {
        eventLogsDao.update(eventLog);
    }

    void createEvent(final EventLog eventLog) {
        eventLogsDao.create(eventLog);
    }

    public List<EventLog> findAll() {
        return eventLogsDao.findAll();
    }

    public EventLog findEvent(final long id) {
        return eventLogsDao.findOne(id);
    }

    public void deleteEventLogById(final long id) {
        eventLogsDao.deleteById(id);
    }

    private void deleteEventLog(final EventLog eventLog) {
        eventLogsDao.delete(eventLog);
    }

    public void clearEvents() {
        List<EventLog> eventLogs = eventLogsDao.findAll();
        for (EventLog eventLog : eventLogs) {
            deleteEventLog(eventLog);
        }
    }
}
