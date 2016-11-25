package trainSimulator.repositories;

import trainSimulator.models.TimetableEntity;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TimetableEntitiesDaoInterface {
    List<TimetableEntity> findAll();

    void create(TimetableEntity timetableEntity);

    TimetableEntity update(TimetableEntity timetableEntity);

    void delete(TimetableEntity timetableEntity);

    TimetableEntity findOne(long id);

    void deleteById(long entityId);
}
