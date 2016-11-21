package trainSimulator.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains")
public class Train extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Route route;
    @OneToMany(mappedBy = "train_timetable", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "train_id")
    private List<TimetableEntity> timetable;
    @OneToMany(mappedBy = "train_passengers", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "train_id")
    private Set<Passenger> passengers;
    @ManyToOne
    private Station currentStation;

    @Override
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

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }
}
