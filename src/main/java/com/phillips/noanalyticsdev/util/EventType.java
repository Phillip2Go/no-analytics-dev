package com.phillips.noanalyticsdev.util;

import javax.persistence.Embeddable;

@Embeddable
public class EventType {
    private String eventType;
    private String eventGroup;

    public EventType() {
    }

    public EventType(String eventType, String eventGroup) {
        this.eventType = eventType;
        this.eventGroup = eventGroup;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventGroup() {
        return eventGroup;
    }

    public void setEventGroup(String eventGroup) {
        this.eventGroup = eventGroup;
    }
}
