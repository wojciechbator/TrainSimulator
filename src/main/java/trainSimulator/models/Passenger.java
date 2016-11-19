package trainSimulator.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity {
    @Size(min = 1, message = "Please, specify at least one character.")
    private String name;
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "passenger_id")
    private Set<Ticket> tickets;
    @ManyToOne()
    private Integer trainID;

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
