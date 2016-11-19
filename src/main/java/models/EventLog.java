package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Entity
@Table(name = "event_register")
public class EventLog {
    @Id
    @GeneratedValue
    private int id;
    private String type;
    private String stationName;
    private Date timestamp;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
