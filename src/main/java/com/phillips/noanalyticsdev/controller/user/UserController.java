package com.phillips.noanalyticsdev.controller.user;

import com.phillips.noanalyticsdev.service.user.UserService;
import com.phillips.noanalyticsdev.util.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "/userproducts/{amount}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUserProducts(@PathVariable int amount, @PathVariable String userId) {
        return userService.getUserProducts(amount, userId);
    }

    @GetMapping(value = "/mostusedtag/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMostUsedTag(@PathVariable String userId) {
        return userService.getMostUsedTag(userId);
    }

    @GetMapping(value = "/usedtagsdesc/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getUsedTagsDesc(@PathVariable String userId) {
        return userService.getUsedTagsDesc(userId);
    }
}
