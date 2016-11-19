package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.TimetableEntity;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TimetableEntityRepository extends JpaRepository<TimetableEntity, Integer> {
    TimetableEntity findOne(String criteria);
}
