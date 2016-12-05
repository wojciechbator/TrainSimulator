package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Train implements Serializable {
    private static final long serialVersionUID = 1L;

    public Train() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @ManyToOne
    private Route route;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<TimetableEntity> timetable;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<Passenger> passengers;
    @ManyToOne
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
