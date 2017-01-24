package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Role;
import trainSimulator.models.User;
import trainSimulator.repositories.RoleDaoInterface;
import trainSimulator.repositories.UserDaoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wojciech on 29.12.16.
 */
@Service("userService")
@Transactional
public class UserService {
    private final RoleDaoInterface roleDaoInterface;
    private final UserDaoInterface userDaoInterface;

    @Autowired
    public UserService(RoleDaoInterface roleDaoInterface, UserDaoInterface userDaoInterface) {
        this.roleDaoInterface = roleDaoInterface;
        this.userDaoInterface = userDaoInterface;
    }

    public List<User> findAll() {
        return userDaoInterface.findAll();
    }

    public User findOne(final long id) {
        return userDaoInterface.findOne(id);
    }

    public void saveUser(final User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDaoInterface.findByName("ROLE_USER").get(0));
        user.setRoles(roles);
        userDaoInterface.update(user);
    }

    public void delete(final User user) {
        userDaoInterface.delete(user);
    }

    public void deleteById(final long id) {
        userDaoInterface.deleteById(id);
    }

    public User findOneByName(String name) {
        return userDaoInterface.findOneByName(name);
    }

}
