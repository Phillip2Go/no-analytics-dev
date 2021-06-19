package com.phillips.noanalyticsdev.controller.analytics;

import com.phillips.noanalyticsdev.service.analytics.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {
    Logger log = LoggerFactory.getLogger(DataController.class);
    @Autowired
    DataService dataService;
/*
    @GetMapping(value = "/total/amount/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getTotalTagAmount(@PathVariable String tag) {
        return dataService.getTotalTagAmount(tag);
    }

 */
}
