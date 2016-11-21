package trainSimulator.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import trainSimulator.repositories.PassengersDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public class UniqueUserValidator implements ConstraintValidator<UniqueUsername, String> {
    private final PassengersDao passengersDao;

    @Autowired
    public UniqueUserValidator(PassengersDao passengersDao) {
        this.passengersDao = passengersDao;
    }

    public void initialize(UniqueUsername uniqueUsername) {

    }

    public boolean isValid(String criteria, ConstraintValidatorContext constraintValidatorContext) {
        return passengersDao == null || passengersDao.findOne(Long.valueOf(criteria)) == null;
    }
}
