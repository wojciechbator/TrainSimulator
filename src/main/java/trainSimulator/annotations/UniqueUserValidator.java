package trainSimulator.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import trainSimulator.repositories.PassengersDaoInterface;
import trainSimulator.repositories.UserDaoInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public class UniqueUserValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserDaoInterface userDaoInterface;

    @Autowired
    public UniqueUserValidator(UserDaoInterface userDaoInterface) {
        this.userDaoInterface = userDaoInterface;
    }

    public void initialize(UniqueUsername uniqueUsername) {

    }

    public boolean isValid(String criteria, ConstraintValidatorContext constraintValidatorContext) {
        return userDaoInterface == null || userDaoInterface.findOne(Long.valueOf(criteria)) == null;
    }
}
