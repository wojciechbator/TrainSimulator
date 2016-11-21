package trainSimulator.models;

import javax.persistence.*;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne()
    private Passenger passenger;
    private float price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
