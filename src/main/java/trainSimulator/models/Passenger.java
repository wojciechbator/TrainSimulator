package trainSimulator.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 1, message = "Please, specify at least one character.")
    private String name;
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets;
    @ManyToOne()
    @JoinColumn(name = "train_id")
    private Train train;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
