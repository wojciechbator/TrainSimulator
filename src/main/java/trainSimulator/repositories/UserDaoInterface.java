package trainSimulator.repositories;

import trainSimulator.models.User;

import java.util.List;

/**
 * Created by wojciech on 29.12.16.
 */
public interface UserDaoInterface {
    List<User> findAll();

    void create(User user);

    User update(User user);

    void delete(User user);

    User findOne(long id);

    void deleteById(long entityId);
}
