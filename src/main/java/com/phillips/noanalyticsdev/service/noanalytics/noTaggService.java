package com.phillips.noanalyticsdev.service.noanalytics;

import com.phillips.noanalyticsdev.dao.event.EventRepo;
import com.phillips.noanalyticsdev.dao.event.EventTrackRepo;
import com.phillips.noanalyticsdev.model.User;
import com.phillips.noanalyticsdev.model.event.Event;
import com.phillips.noanalyticsdev.model.event.EventTrack;
import com.phillips.noanalyticsdev.service.UserService;
import com.phillips.noanalyticsdev.util.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class noTaggService {
    Logger log = LoggerFactory.getLogger(noTaggService.class);
    @Autowired
    EventRepo eventRepo;
    @Autowired
    EventTrackRepo eventTrackRepo;
    @Autowired
    UserService userService;

    public void taggEvent(Map<String, String> data) {
        User user = userService.getUser(data);
        Event event = eventRepo.findByEventName(data.get("eventName"));
        if (event == null) {
            Event createdEvent = createEvent(data);
            createEventTrack(createdEvent, user, data);
        } else {
            addCount(event);
            createEventTrack(event, user, data);
        }
    }

    public Event createEvent(Map<String, String> data) {
        Event event = new Event(data.get("eventName"), data.get("eventType"), data.get("pathname"), data.get("pagetitle"));
        eventRepo.save(event);
        return event;
    }

    public void addCount(Event event) {
        int counter = event.getCount();
        event.setCount(counter + 1);
        eventRepo.save(event);
    }

    public void createEventTrack(Event event, User user, Map<String, String> data) {
        if (data.get("contentType").equals("product-item")) {
            Product product = new Product(data.get("contentName"), data.get("contentTag"));
            EventTrack eventTrack = new EventTrack(event, user, product, data.get("contentTag"), data.get("timestamp"));
            eventTrackRepo.save(eventTrack);
        } else {
            EventTrack eventTrack = new EventTrack(event, user, data.get("eventTag"), data.get("timestamp"));
            eventTrackRepo.save(eventTrack);
        }
    }
}
