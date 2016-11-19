package trainSimulator.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "routes")
public class Route extends BaseEntity {
    @OneToMany(mappedBy = "stations_on_route", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "route_id")
    private Set<Station> stationsOnRoute;
    @OneToMany(mappedBy = "trains_on_route", cascade = CascadeType.REMOVE)
    @JoinColumn(name = "route_id")
    private Set<Train> trainsOnRoute;
    private boolean isAvailable;

    public Set<Station> getStationsOnRoute() {
        return stationsOnRoute;
    }

    public void setStationsOnRoute(Set<Station> stationsOnRoute) {
        this.stationsOnRoute = stationsOnRoute;
    }

    public Set<Train> getTrainsOnRoute() {
        return trainsOnRoute;
    }

    public void setTrainsOnRoute(Set<Train> trainsOnRoute) {
        this.trainsOnRoute = trainsOnRoute;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
