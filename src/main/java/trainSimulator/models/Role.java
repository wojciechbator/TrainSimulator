package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wojciech on 29.12.16.
 */
@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
