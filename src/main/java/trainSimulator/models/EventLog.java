package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Entity
@Table(name = "event_register", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class EventLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public EventLog() {
        super();
    }

    public EventLog(String type, String stationName, Date timestamp, String comment) {
        this.type = type;
        this.stationName = stationName;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "station_name")
    private String stationName;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "comment")
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", type: " + type + ", station id: " + stationName + ", timestamp: " + timestamp.toString()
                + ", comment: " + comment;
    }
}
