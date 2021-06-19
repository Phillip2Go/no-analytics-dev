package com.phillips.noanalyticsdev.service.analytics;

import com.phillips.noanalyticsdev.dao.event.EventRepo;
import com.phillips.noanalyticsdev.dao.event.EventTrackRepo;
import com.phillips.noanalyticsdev.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    Logger log = LoggerFactory.getLogger(DataService.class);
    /*
    @Autowired
    EventRepo eventRepo;
    @Autowired
    EventTrackRepo eventTrackRepo;

    public int getTotalTagAmount(String tag) {
        List<Event> allEvents = eventRepo.findAllByEventTag(tag);
        int counter = 0;

        for (Event events: allEvents) {
            counter += events.getCount();
        }
        log.info("coooounter" + counter);

        return counter;
    }
    */

}
