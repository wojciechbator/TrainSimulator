package services;

import models.TimetableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TimetableEntityRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class TimetableEntityService {
    private final TimetableEntityRepository timetableEntityRepository;

    @Autowired
    public TimetableEntityService(TimetableEntityRepository timetableEntityRepository) {
        this.timetableEntityRepository = timetableEntityRepository;
    }

    public void saveTimetable(List<TimetableEntity> timetable) {
        timetableEntityRepository.save(timetable);
    }

    public void deleteTimetableEntity(TimetableEntity timetableEntity) {
        timetableEntityRepository.delete(timetableEntity);
    }

    public void findTimetableEntity(Integer id) {
        timetableEntityRepository.findOne(String.valueOf(id));
    }

    public void clearTimetableEntitys() {
        List<TimetableEntity> allTimetableEntities = timetableEntityRepository.findAll();
        allTimetableEntities.forEach(this::deleteTimetableEntity);
    }
}
