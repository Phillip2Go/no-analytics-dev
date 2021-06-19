package com.phillips.noanalyticsdev.controller;

import com.phillips.noanalyticsdev.service.UserService;
import com.phillips.noanalyticsdev.util.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping(value = "/cookieUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public void cookieUserId(@RequestBody Map<String, String> data) {
        userService.visitUser(data);
    }

    @GetMapping(value = "/userproducts/{amount}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductItem> getUserProducts(@PathVariable int amount, @PathVariable String userId) {
        return userService.getUserProducts(amount, userId);
    }
}
