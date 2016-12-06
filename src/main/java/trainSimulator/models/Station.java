package trainSimulator.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    private List<Train> trainsOnStation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Route route;

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
}
