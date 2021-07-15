package com.phillips.noanalyticsdev.service.analytics;

import com.phillips.noanalyticsdev.dao.event.EventRepo;
import com.phillips.noanalyticsdev.dao.event.EventTrackRepo;
import com.phillips.noanalyticsdev.model.user.User;
import com.phillips.noanalyticsdev.model.event.Event;
import com.phillips.noanalyticsdev.model.event.EventTrack;
import com.phillips.noanalyticsdev.service.user.UserService;
import com.phillips.noanalyticsdev.util.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrackingService {
    Logger log = LoggerFactory.getLogger(TrackingService.class);
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private EventTrackRepo eventTrackRepo;
    @Autowired
    private UserService userService;
    private static final String CONTENT_TYPE = "contentType";
    private static final String EVENT_NAME = "eventName";
    private static final String PRODUCT_ITEM = "product-item";
    private static final String EVENT_TYPE = "eventType";
    private static final String PATH_NAME = "pathname";
    private static final String PAGE_TITLE = "pagetitle";
    private static final String CONTENT_TAG = "contentTag";
    private static final String EVENT_TAG = "eventTag";
    private static final String CONTENT_NAME = "contentName";
    private static final String TIMESTAMP = "timestamp";

    public void visitUser(Map<String, String> data) {
        userService.checkUser(data);
    }

    public void trackEvent(Map<String, String> data) {
        User user = userService.getUser(data);
        Event event = eventRepo.findByEventName(data.get(EVENT_NAME));
        if (event == null) {
            Event createdEvent = createEvent(data);
            createEventTrack(createdEvent, user, data);
        } else {
            addEventCount(event);
            createEventTrack(event, user, data);
        }
    }

    public Event createEvent(Map<String, String> data) {
        Event event;

        if (data.get(CONTENT_TYPE).equals(PRODUCT_ITEM)) {
            event = new Event(data.get(EVENT_NAME), data.get(EVENT_TYPE), data.get(PATH_NAME), data.get(PAGE_TITLE), data.get(CONTENT_TAG));
        } else {
            event = new Event(data.get(EVENT_NAME), data.get(EVENT_TYPE), data.get(PATH_NAME), data.get(PAGE_TITLE), data.get(EVENT_TAG));
        }
        eventRepo.save(event);

        return event;
    }

    public void addEventCount(Event event) {
        int counter = event.getCount();
        event.setCount(counter + 1);
        eventRepo.save(event);
    }

    public void createEventTrack(Event event, User user, Map<String, String> data) {
        if (data.get(CONTENT_TYPE).equals(PRODUCT_ITEM)) {
            Product product = new Product(data.get(CONTENT_NAME), data.get(CONTENT_TAG));
            EventTrack eventTrack = new EventTrack(event, user, product, data.get(CONTENT_TAG), data.get(TIMESTAMP));
            eventTrackRepo.save(eventTrack);
        } else {
            EventTrack eventTrack = new EventTrack(event, user, data.get(EVENT_TAG), data.get(TIMESTAMP));
            eventTrackRepo.save(eventTrack);
        }
    }
}
