package com.phillips.noanalyticsdev.controller.noanalytics;

import com.phillips.noanalyticsdev.service.noanalytics.noTaggService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/notagg")
public class noTaggController {
    Logger log = LoggerFactory.getLogger(noTaggController.class);
    @Autowired
    noTaggService trackService;

    @PostMapping(value = "/track", produces = MediaType.APPLICATION_JSON_VALUE)
    public void track(@RequestBody Map<String, String> data) {
        log.info("noTagg Event:" + data);
        trackService.taggEvent(data);
    }
}



