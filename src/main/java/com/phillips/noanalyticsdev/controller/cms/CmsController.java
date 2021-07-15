package com.phillips.noanalyticsdev.controller.cms;

import com.phillips.noanalyticsdev.service.user.UserService;
import com.phillips.noanalyticsdev.service.cms.CmsService;
import com.phillips.noanalyticsdev.util.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/shops")
public class CmsController {
    Logger log = LoggerFactory.getLogger(CmsController.class);
    @Autowired
    private CmsService cmsService;
    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getAllShops() {
        return cmsService.getAllShops();
    }

    @GetMapping(value = "/no-beats/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductItem> getAllProducts() {
        return cmsService.getAllProducts();
    }

    @GetMapping(value = "/no-beats/products/random/amount/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductItem> getRandomAmountProducts(@PathVariable int amount) {
        return cmsService.getRandomAmountProducts(amount);
    }

    @GetMapping(value = "/no-beats/products/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductItem> getProductsByTag(@PathVariable String tag) {
        return cmsService.getProductsByTag(tag);
    }

    @GetMapping(value = "/no-beats/ads", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getAllAds() {
        return cmsService.getAllAds();
    }

    @GetMapping(value = "/no-beats/ads/tag/{tag}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getAdsByTag(@PathVariable String tag) {
        return cmsService.getAdsByTag(tag);
    }

    @GetMapping(value = "/no-beats/ads/type/{adType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getAdsByAdType(@PathVariable String adType) {
        return cmsService.getAdsByAdType(adType);
    }
}
