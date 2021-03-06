package trainSimulator.models;

import trainSimulator.utilities.TrainState;

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
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Route route;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private List<TimetableEntity> timetable;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private Set<Passenger> passengers;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Station station;
    @Column(name = "on_station_state")
    private TrainState onStationState;


    public TrainState getStateForStation(Station station) {
        // TODO: maybe problematic
        if (this.station.getId() == station.getId()) {
            return onStationState;
        } else
            return null;
    }

    public void setStateForStation(Station station, TrainState state) {
        if (this.station.getId() == station.getId()) {
            this.onStationState = state;
        }
        else this.onStationState = TrainState.PLANNED;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
