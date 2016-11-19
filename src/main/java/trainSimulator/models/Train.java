package trainSimulator.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy = "train_timetable", cascade = CascadeType.REMOVE)
    private List<TimetableEntity> timetable;
    @OneToMany(mappedBy = "train_passengers", cascade = CascadeType.REMOVE)
    private List<Passenger> passengers;
    @ManyToOne
    @JoinColumn(name = "Station_id")
    private Station currentStation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }
}
