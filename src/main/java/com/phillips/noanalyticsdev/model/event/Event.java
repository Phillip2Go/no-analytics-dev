package com.phillips.noanalyticsdev.model.event;

import com.phillips.noanalyticsdev.util.OnPage;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Event implements Serializable {
    @Id
    private String eventName;
    @NotNull
    @Size(max = 32)
    private String eventType;
    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    private List<EventTrack> eventTracks;
    @Size(max = 32)
    private String pathname;
    @Size(max = 32)
    private String pagetitle;
    @Min(1)
    private int count; // eventTracks.length
    @NotNull
    @Size(max = 32)
    private String tag;
    // @Embedded
    // private OnPage onPage;

    public Event() {
    }

    public Event(String eventName, String eventType, String pathname, String pagetitle, String tag) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.pathname = pathname;
        this.pagetitle = pagetitle;
        this.tag = tag;
        this.count = 1;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<EventTrack> getEventTracks() {
        return eventTracks;
    }

    public void setEventTracks(List<EventTrack> eventTracks) {
        this.eventTracks = eventTracks;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getPagetitle() {
        return pagetitle;
    }

    public void setPagetitle(String pagetitle) {
        this.pagetitle = pagetitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Event[" + "eventName=" + this.eventName + "]";
    }
}
