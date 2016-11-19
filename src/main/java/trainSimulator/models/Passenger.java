package trainSimulator.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by mitron-wojtek on 15.11.16.
 */
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 1, message = "Please, specify at least one character.")
    private String name;
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "passenger_id")
    private List<Ticket> tickets;
    @ManyToOne()
    private Integer trainID;

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
