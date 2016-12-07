package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.TimetableEntity;
import trainSimulator.repositories.TimetableEntitiesDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TimetableEntityService {
    private final TimetableEntitiesDaoInterface timetableEntitiesDaoInterface;

    @Autowired
    public TimetableEntityService(TimetableEntitiesDaoInterface timetableEntitiesDao) {
        this.timetableEntitiesDaoInterface = timetableEntitiesDao;
    }

    public void saveTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.update(timetableEntity);
    }

    void createTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.create(timetableEntity);
    }

    private void deleteTimetableEntity(final TimetableEntity timetableEntity) {
        timetableEntitiesDaoInterface.delete(timetableEntity);
    }

    public void deleteTimetableEntityById(final long id) {
        timetableEntitiesDaoInterface.deleteById(id);
    }

    public List<TimetableEntity> getAllTimetableEntities() {
        return timetableEntitiesDaoInterface.findAll();
    }

    public TimetableEntity findTimetableEntity(final long id) {
        return timetableEntitiesDaoInterface.findOne(id);
    }

    public void clearTimetableEntities() {
        List<TimetableEntity> allTimetableEntities = timetableEntitiesDaoInterface.findAll();
        for (TimetableEntity timetableEntity : allTimetableEntities) {
            deleteTimetableEntity(timetableEntity);
        }
    }
}
