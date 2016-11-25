package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "routes")
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;

    public Route() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    private Long id;
    @OneToMany(mappedBy = "route", targetEntity = Station.class, fetch = FetchType.EAGER)
    private List<Station> stationsOnRoute;
    @OneToMany(mappedBy = "route", targetEntity = Train.class, fetch = FetchType.EAGER)
    private List<Train> trainsOnRoute;
    @Column(name = "is_available")
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
