package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "timetable")
public class TimetableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public TimetableEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timetable_id")
    private long id;
    @ManyToOne(targetEntity = Train.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private Train train;
    @ManyToOne(targetEntity = Station.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
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
