package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "stations", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;

    public Station() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private Set<Train> trainsOnStation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Route route;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
//    private Set<TimetableEntity> timetableForStation;

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

//    public Set<TimetableEntity> getTimetableForStation() {
//        return timetableForStation;
//    }
//
//    public void setTimetableForStation(Set<TimetableEntity> timetableForStation) {
//        this.timetableForStation = timetableForStation;
//    }
}
