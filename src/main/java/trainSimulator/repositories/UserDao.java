package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

/**
 * Created by wojciech on 29.12.16.
 */
@Repository
public class UserDao extends AbstractJpaDao<User> implements UserDaoInterface {
    @PersistenceContext
    EntityManager entityManager;

    public UserDao() {
        super();
        this.setClazz(User.class);
    }

    @Override
    public User findOneByName(String name) {
        for(User user : findAll()) {
            if (Objects.equals(user.getName(), name)) {
                return user;
            }
        }
        return null;
    }
}
