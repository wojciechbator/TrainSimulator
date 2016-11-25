package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    public Ticket() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private Long id;
    @ManyToOne(targetEntity = Passenger.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @Column(name = "price")
    private float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passengerId) {
        this.passenger = passengerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
