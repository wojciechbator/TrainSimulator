package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.TimetableEntity;
import trainSimulator.repositories.TimetableEntitiesDao;
import trainSimulator.repositories.implementations.TimetableEntitiesDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TimetableEntityService {
    private final TimetableEntitiesDao timetableEntitiesDao;

    @Autowired
    public TimetableEntityService(TimetableEntitiesDaoImplementation timetableEntitiesDao) {
        this.timetableEntitiesDao = timetableEntitiesDao;
    }

    public void saveTimetable(List<TimetableEntity> timetable) {
        timetableEntitiesDao.saveOrUpdate(timetable);
    }

    public void deleteTimetableEntity(Long id) {
        timetableEntitiesDao.delete(id);
    }

    public void findTimetableEntity(Long id) {
        timetableEntitiesDao.findOne(id);
    }

    public void clearTimetableEntitys() {
        List<TimetableEntity> allTimetableEntities = timetableEntitiesDao.findAll();
        for (TimetableEntity timetableEntity : allTimetableEntities) {
            deleteTimetableEntity(timetableEntity.getId());
        }
    }
}
