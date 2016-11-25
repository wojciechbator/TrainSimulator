package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains")
public class Train implements Serializable {
    private static final long serialVersionUID = 1L;

    public Train() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_id")
    private long id;
    @ManyToOne(targetEntity = Route.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy = "train", targetEntity = TimetableEntity.class, fetch = FetchType.EAGER)
    private List<TimetableEntity> timetable;
    @OneToMany(mappedBy = "train", targetEntity = Passenger.class, fetch = FetchType.EAGER)
    private Set<Passenger> passengers;
    @ManyToOne(targetEntity = Station.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private Station station;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<TimetableEntity> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<TimetableEntity> timetable) {
        this.timetable = timetable;
        for (TimetableEntity timetableEntity : timetable) {
            timetableEntity.setTrain(this);
        }
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
