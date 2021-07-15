package com.phillips.noanalyticsdev.service.analytics;

import com.phillips.noanalyticsdev.dao.event.EventRepo;
import com.phillips.noanalyticsdev.dao.event.EventTrackRepo;
import com.phillips.noanalyticsdev.model.event.Event;
import com.phillips.noanalyticsdev.model.event.EventTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    Logger log = LoggerFactory.getLogger(DataService.class);
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private EventTrackRepo eventTrackRepo;

    public int getTotalTagAmount(String tag) {
        List<Event> allEvents = eventRepo.findAllByTag(tag);
        int counter = 0;

        for (Event events: allEvents) {
            counter += events.getCount();
        }

        return counter;
    }

    public int getTotalTagPurchase(String tag) {
        List<EventTrack> allPurchase = eventTrackRepo.findAllByProduct_ProductTag(tag);
        return allPurchase.size();
    }

    public List<EventTrack> getLatestOrders(int amout) {
        List<EventTrack> allOrders = eventTrackRepo.findAllByProduct_NotNull();
        List<EventTrack> latestOrders = new ArrayList<EventTrack>();
        for (int i = (allOrders.size() - 1); i > ((allOrders.size() - 1) - amout); i--) {
            latestOrders.add(allOrders.get(i));
        }
        return latestOrders;
    }
}
