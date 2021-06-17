package com.phillips.noanalyticsdev.dao.event;

import com.phillips.noanalyticsdev.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, String> {
    Event findByEventName(String eventName);
}
