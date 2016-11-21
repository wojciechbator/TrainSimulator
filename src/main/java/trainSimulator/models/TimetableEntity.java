package trainSimulator.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "timetable")
public class TimetableEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Train train;
    @ManyToOne
    private Station station;
    private Date arrivalTime;
    private Date departureTime;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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
