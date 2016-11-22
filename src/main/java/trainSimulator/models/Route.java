package trainSimulator.models;

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
    private Long id;
    @OneToMany(mappedBy = "stations_on_route", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "route_id")
    private List<Station> stationsOnRoute;
    @OneToMany(mappedBy = "trains_on_route", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "route_id")
    private List<Train> trainsOnRoute;
    private boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
