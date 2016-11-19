package repository;

import models.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TimetableEntityRepository extends JpaRepository<TimetableEntity, Integer> {
    TimetableEntity findOne(String criteria);
}
