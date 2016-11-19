package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "trains_on_station")
    private List<Train> trainsOnStation;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @OneToOne
    @JoinColumn(name = "timetable_for_station")
    private List<TimetableEntity> timetableForStation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Train> getTrainsOnStation() {
        return trainsOnStation;
    }

    public void setTrainsOnStation(List<Train> trainsOnStation) {
        this.trainsOnStation = trainsOnStation;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<TimetableEntity> getTimetableForStation() {
        return timetableForStation;
    }

    public void setTimetableForStation(List<TimetableEntity> timetableForStation) {
        this.timetableForStation = timetableForStation;
    }
}
