package trainSimulator.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trains_on_route")
    private Route route;
    @OneToMany(mappedBy = "train", cascade = CascadeType.REMOVE)
    private List<TimetableEntity> timetable;
    @OneToMany(mappedBy = "train", cascade = CascadeType.REMOVE)
    private Set<Passenger> passengers;
    @ManyToOne
    private Station station;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
