package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "stations")
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;

    public Station() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "station_id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "station", targetEntity = Train.class, fetch = FetchType.EAGER)
    private Set<Train> trainsOnStation;
    @ManyToOne(targetEntity = Route.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToMany(mappedBy = "station", targetEntity = TimetableEntity.class, fetch = FetchType.EAGER)
    private Set<TimetableEntity> timetableForStation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
