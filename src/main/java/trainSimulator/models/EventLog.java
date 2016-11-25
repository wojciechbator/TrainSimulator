package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Entity
@Table(name = "event_register")
public class EventLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public EventLog() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eventlog_id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "Station_name")
    private String stationName;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
