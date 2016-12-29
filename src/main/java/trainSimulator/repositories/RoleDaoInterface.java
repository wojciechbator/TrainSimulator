package trainSimulator.repositories;

import trainSimulator.models.Role;

import java.util.List;

/**
 * Created by wojciech on 29.12.16.
 */
public interface RoleDaoInterface {
    List<Role> findAll();

    void create(Role role);

    Role update(Role role);

    void delete(Role role);

    Role findOne(long id);

    void deleteById(long entityId);

    List<Role> findByName(String name);
}
