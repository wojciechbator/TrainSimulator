package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "stations_on_route", cascade = CascadeType.REMOVE)
    private List<Station> stationsOnRoute;
    @OneToMany(mappedBy = "trains_on_route", cascade = CascadeType.REMOVE)
    private List<Train> trainsOnRoute;
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Station> getStationsOnRoute() {
        return stationsOnRoute;
    }

    public void setStationsOnRoute(List<Station> stationsOnRoute) {
        this.stationsOnRoute = stationsOnRoute;
    }

    public List<Train> getTrainsOnRoute() {
        return trainsOnRoute;
    }

    public void setTrainsOnRoute(List<Train> trainsOnRoute) {
        this.trainsOnRoute = trainsOnRoute;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
