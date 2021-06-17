package com.phillips.noanalyticsdev.model;

import com.phillips.noanalyticsdev.model.event.EventTrack;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    private String userId;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<EventTrack> eventTracks;
    @Min(1)
    private int visits;
    // @DateTimeFormat(pattern="dd-MM-yyyy HH-mm-ss-ns")
    // @PastOrPresent
    private String lastVisit;
    // @DateTimeFormat(pattern="dd-MM-yyyy HH-mm-ss-ns")
    // @PastOrPresent
    private String createDate;

    public User() {
    }

    public User(String userId, String lastVisit, String createDate) {
        this.userId = userId;
        this.visits = 1;
        this.lastVisit = lastVisit;
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<EventTrack> getEventTracks() {
        return eventTracks;
    }

    public void setEventTracks(List<EventTrack> eventTracks) {
        this.eventTracks = eventTracks;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User[" + "userid=" + this.userId + "]";
    }
}
