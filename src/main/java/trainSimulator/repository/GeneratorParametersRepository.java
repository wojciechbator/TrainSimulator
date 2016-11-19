package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.GeneratorParameter;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Repository
public interface GeneratorParametersRepository extends JpaRepository<GeneratorParameter, Integer> {
    GeneratorParameter findOne(String criteria);
}
