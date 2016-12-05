package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "tickets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    public Ticket() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @ManyToOne
    private Passenger passenger;
    @Column(name = "price")
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passengerId) {
        this.passenger = passengerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
