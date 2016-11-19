package trainSimulator.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains")
public class Train extends BaseEntity {
    @ManyToOne
    private Route route;
    @OneToMany(mappedBy = "train_timetable", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "train_id")
    private Set<TimetableEntity> timetable;
    @OneToMany(mappedBy = "train_passengers", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "train_id")
    private Set<Passenger> passengers;
    @ManyToOne
    private Station currentStation;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Set<TimetableEntity> getTimetable() {
        return timetable;
    }

    public void setTimetable(Set<TimetableEntity> timetable) {
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
