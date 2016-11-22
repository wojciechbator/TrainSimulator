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
    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    private List<Station> stationsOnRoute;
    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    private List<Train> trainsOnRoute;
    private boolean available;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
