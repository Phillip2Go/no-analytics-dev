package com.phillips.noanalyticsdev.model.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.phillips.noanalyticsdev.model.user.User;
import com.phillips.noanalyticsdev.util.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class EventTrack implements Serializable {
    @Id @GeneratedValue
    private Long trackId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_event_name", nullable=false)
    @JsonBackReference
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_user_id", nullable=false)
    @JsonBackReference
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private EventActionProps eventProps;
    @NotNull
    @Size(max = 32)
    private String tag;
    @Embedded
    private Product product;
    // @DateTimeFormat(pattern="dd-MM-yyyy HH-mm-ss-ns") @PastOrPresent
    private String timeStamp;

    public EventTrack() {
    }

    public EventTrack(Event event, User user, String tag, String timeStamp) {
        this.event = event;
        this.user = user;
        this.tag = tag;
        // this.eventProps = eventProps;
        this.timeStamp = timeStamp;
    }

    public EventTrack(Event event, User user, Product product, String tag, String timeStamp) {
        this.event = event;
        this.user = user;
        this.product = product;
        this.tag = tag;
        // this.eventProps = eventProps;
        this.timeStamp = timeStamp;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventActionProps getEventProps() {
        return eventProps;
    }

    public void setEventProps(EventActionProps eventProps) {
        this.eventProps = eventProps;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "EventTrack[" + "trackId=" + this.trackId + ", event=" + event + ", user=" + user + "]";
    }
}
