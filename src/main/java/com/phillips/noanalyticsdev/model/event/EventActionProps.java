package com.phillips.noanalyticsdev.model.event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class EventActionProps implements Serializable {
    @Id
    @Column(name = "action_Name_Id")
    private String actionName;
    @OneToOne(mappedBy = "eventProps")
    private EventTrack eventTrack;
    @ElementCollection
    private Map<String, String> props;

    public EventActionProps() {
    }

    public EventActionProps(String actionName, EventTrack eventTrack, Map<String, String> props) {
        this.actionName = actionName;
        this.eventTrack = eventTrack;
        this.props = props;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public EventTrack getEventTrack() {
        return eventTrack;
    }

    public void setEventTrack(EventTrack eventTrack) {
        this.eventTrack = eventTrack;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "EventActionProp[" + "actionName=" + this.actionName + "]";
    }
}
