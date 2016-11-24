package trainSimulator.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "station", targetEntity = Train.class, fetch = FetchType.EAGER)
    private Set<Train> trainsOnStation;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy = "station", targetEntity = TimetableEntity.class, fetch = FetchType.EAGER)
    private Set<TimetableEntity> timetableForStation;

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
