package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by wojciech on 29.12.16.
 */
@Repository
public class RoleDao extends AbstractJpaDao<Role> implements RoleDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public RoleDao() {
        super();
        setClazz(Role.class);
    }

    @Override
    public List<Role> findByName(String name) {
        List<Role> allRoles = findAll();
        List<Role> searchedRoles = new ArrayList<>();
        for (Role role : allRoles) {
            if (Objects.equals(role.getName(), name)) {
                searchedRoles.add(role);
            }
        }
        return searchedRoles;
    }
}
