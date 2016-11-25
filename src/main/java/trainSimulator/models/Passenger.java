package trainSimulator.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Entity
@Table(name = "passengers")
public class Passenger implements Serializable {
    private static final long serialVersionUID = 1L;

    public Passenger() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 1, message = "Please, specify at least one character.")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "passenger", targetEntity = Ticket.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
    @ManyToOne(targetEntity = Train.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private Train train;

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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
