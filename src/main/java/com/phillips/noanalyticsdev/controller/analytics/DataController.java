package com.phillips.noanalyticsdev.controller.analytics;

import com.phillips.noanalyticsdev.model.event.EventTrack;
import com.phillips.noanalyticsdev.service.analytics.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {
    Logger log = LoggerFactory.getLogger(DataController.class);
    @Autowired
    private DataService dataService;

    @GetMapping(value = "/total/amount/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getTotalTagAmount(@PathVariable String tag) {
        return dataService.getTotalTagAmount(tag);
    }

    @GetMapping(value = "/total/amount/purchase/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getTotalTagPurchase(@PathVariable String tag) {
        return dataService.getTotalTagPurchase(tag);
    }

    @GetMapping(value = "/latest/orders/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventTrack> getLatestOrders(@PathVariable int amount) {
        return dataService.getLatestOrders(amount);
    }
}
