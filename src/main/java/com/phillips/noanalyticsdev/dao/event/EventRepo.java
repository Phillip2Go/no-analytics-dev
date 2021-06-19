package com.phillips.noanalyticsdev.dao.event;

import com.phillips.noanalyticsdev.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, String> {
    Event findByEventName(String eventName);
    List<Event> findAllByTag(String tag);
}
