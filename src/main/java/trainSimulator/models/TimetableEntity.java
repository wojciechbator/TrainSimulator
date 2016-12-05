package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "timetable", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class TimetableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public TimetableEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Train train;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Station station;
    @Column(name = "arrival_time")
    private Date arrivalTime;
    @Column(name = "departure_time")
    private Date departureTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train trainId) {
        this.train = trainId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}
