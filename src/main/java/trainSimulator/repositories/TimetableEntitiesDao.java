package trainSimulator.repositories;

import trainSimulator.models.TimetableEntity;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TimetableEntitiesDao {
    List<TimetableEntity> findAll();

    TimetableEntity get(Long id);

    void saveOrUpdate(List<TimetableEntity> timetableEntities);

    void delete(Long id);

    TimetableEntity findOne(Long id);
}
