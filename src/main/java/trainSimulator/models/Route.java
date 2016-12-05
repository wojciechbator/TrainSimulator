package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Entity
@Table(name = "routes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;

    public Route() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Station> stationsOnRoute;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Train> trainsOnRoute;
    @Column(name = "is_available")
    private boolean available;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
