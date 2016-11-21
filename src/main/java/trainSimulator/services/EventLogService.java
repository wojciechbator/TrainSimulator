package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;
import trainSimulator.repositories.EventLogsDao;
import trainSimulator.repositories.implementations.EventLogsDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
class EventLogService {
    private final EventLogsDao eventLogsDao;

    @Autowired
    public EventLogService(EventLogsDaoImplementation eventLogsDao) {
        this.eventLogsDao = eventLogsDao;
    }

    void saveEvent(EventLog eventLog) {
        eventLogsDao.saveOrUpdate(eventLog);
    }

    public EventLog findEvent(Long id) {
        return eventLogsDao.findOne(id);
    }

    private void deleteEventLog(Long id) {
        eventLogsDao.delete(id);
    }

    void clearEvents() {
        List<EventLog> eventLogs = eventLogsDao.findAll();
        for (EventLog eventLog : eventLogs) {
            deleteEventLog(eventLog.getId());
        }
    }
}
