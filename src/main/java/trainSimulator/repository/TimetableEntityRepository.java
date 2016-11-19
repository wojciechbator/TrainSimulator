package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.TimetableEntity;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface TimetableEntityRepository extends JpaRepository<TimetableEntity, Integer> {
    TimetableEntity findOne(String criteria);
}
