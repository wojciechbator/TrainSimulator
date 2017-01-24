package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.TimetableEntity;
import trainSimulator.repositories.TimetableEntitiesDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service("timetableEntityService")
@Transactional
public class TimetableEntityService {
    private static final Logger logger = Logger.getLogger(TimetableEntityService.class);
    private final TimetableEntitiesDaoInterface timetableEntitiesDaoInterface;

    @Autowired
    public TimetableEntityService(TimetableEntitiesDaoInterface timetableEntitiesDao) {
        this.timetableEntitiesDaoInterface = timetableEntitiesDao;
    }

    public void saveTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.update(timetableEntity);
        logger.info("Saved timetable entity: " + timetableEntity.getId());
    }

    void createTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.create(timetableEntity);
        logger.info("Created timetable entity: " + timetableEntity.getId());
    }

    private void deleteTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.delete(timetableEntity);
        logger.info("Deleted timetable entity: " + timetableEntity.getId());
    }

    public void deleteTimetableEntityById(final long id) {
        timetableEntitiesDaoInterface.deleteById(id);
        logger.info("Deleted timetable entity by id: " + id);
    }

    public List<TimetableEntity> getAllTimetableEntities() {
        return timetableEntitiesDaoInterface.findAll();
    }

    public TimetableEntity findTimetableEntity(final long id) {
        logger.info("Found timetable entity with id: " + id);
        return timetableEntitiesDaoInterface.findOne(id);
    }

    public void clearTimetableEntities() {
        List<TimetableEntity> allTimetableEntities = timetableEntitiesDaoInterface.findAll();
        for (TimetableEntity timetableEntity : allTimetableEntities) {
            deleteTimetableEntity(timetableEntity);
        }
        logger.info("Cleared all timetable entities!");
    }
}
