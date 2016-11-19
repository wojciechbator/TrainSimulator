package trainSimulator.models;

import javax.persistence.*;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne()
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
