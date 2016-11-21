package trainSimulator.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "stations")
public class Station extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "trains_on_station")
    @JoinColumn(name = "station_id")
    private Set<Train> trainsOnStation;
    @ManyToOne
    private Route route;
    @OneToMany(mappedBy = "timetable_for_station")
    @JoinColumn(name = "station_id")
    private Set<TimetableEntity> timetableForStation;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Train> getTrainsOnStation() {
        return trainsOnStation;
    }

    public void setTrainsOnStation(Set<Train> trainsOnStation) {
        this.trainsOnStation = trainsOnStation;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Set<TimetableEntity> getTimetableForStation() {
        return timetableForStation;
    }

    public void setTimetableForStation(Set<TimetableEntity> timetableForStation) {
        this.timetableForStation = timetableForStation;
    }
}
