package trainSimulator.models;

import org.hibernate.validator.constraints.Email;
import trainSimulator.annotations.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wojciech on 29.12.16.
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Size(min = 3, message = "Przynajmniej 3 znaki")
    @Column(unique = true)
    @UniqueUsername(message = "Ta nazwa jest już zarezerwowana")
    private String name;

    @Size(min = 3, message = "Zły mail, jeszcze raz")
    @Email(message = "Zły mail, jeszcze raz")
    @Column(unique = true)
    @UniqueUsername(message = "Taki mail jest już zarezerwowany")
    private String email;

    @Size(min = 5, message = "Weź przynajmniej 5 znaków")
    private String password;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets;

    @ManyToMany
    @JoinTable
    private List<Role> roles;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
