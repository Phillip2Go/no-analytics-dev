package com.phillips.noanalyticsdev.service;

import com.phillips.noanalyticsdev.dao.UserRepo;
import com.phillips.noanalyticsdev.model.User;
import com.phillips.noanalyticsdev.model.event.EventTrack;
import com.phillips.noanalyticsdev.service.cms.CmsService;
import com.phillips.noanalyticsdev.util.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    CmsService cmsService;
    @Autowired
    UserRepo userRepo;

    public List<ProductItem> getUserProducts(int amount, String userId) {
        Map<String, Integer> userTags = getUserTags(userId);
        Map<String, Integer> productAmount = getProductAmount(amount, userTags);
        Map<String, Integer> userPurchase = getUserPurchase(userId);
        List<ProductItem> items;
        List<ProductItem> userProducts = new ArrayList<>();

        if (!userTracksExist(userId)) {
            return null;
        }

        for (String key: productAmount.keySet()) {
            if (productAmount.get(key) > 0) {
                items = cmsService.getProductsByTag(key);
                int i = 0;
                while (i < productAmount.get(key)) {
                    int rand = (int) (Math.random() * items.size() + 0);
                    if (userPurchase.get(items.get(rand).getTrackname()) != null) {
                        items.remove(items.get(rand));
                    } else {
                        userProducts.add(items.get(rand));
                        items.remove(items.get(rand));
                        i++;
                    }
                }
            }
        }

        return shuffleUserProsucts(userProducts);
    }

    public List<ProductItem> shuffleUserProsucts(List<ProductItem> items) {
        List<ProductItem> shuffleItems = new ArrayList<>();
        int i = 0;
        int size = items.size();

        while (i < size) {
            int rand = (int) (Math.random() * items.size() + 0);
            shuffleItems.add(items.get(rand));
            items.remove(items.get(rand));
            i++;
        }

        return shuffleItems;
    }

    public boolean userTracksExist(String userId) {
        boolean userExist = userRepo.existsById(userId);

        if (!userExist) {
            return false;
        }
        else {
            User user = getOneUser(userId);
            if (user.getEventTracks() == null) {
                return false;
            }
        }

        return true;
    }

    public Map<String, Integer> getUserTags(String userId) {
        boolean userExist = userRepo.existsById(userId);

        if (!userExist) {
            return null;
        }

        User user = getOneUser(userId);
        Map<String, Integer> userTags = new HashMap<String, Integer>();
        for (EventTrack track : user.getEventTracks()) {
            if (track.getProduct() != null) {
                if (userTags.get(track.getTag()) == null) {
                    userTags.put(track.getTag(), 5);
                } else {
                    int add = userTags.get(track.getTag()) + 5;
                    userTags.put(track.getTag(), add);
                }
            } else {
                if (userTags.get(track.getTag()) == null) {
                    userTags.put(track.getTag(), 1);
                } else {
                    int add = userTags.get(track.getTag()) + 1;
                    userTags.put(track.getTag(), add);
                }
            }
        }

        log.info("total user tags: " + userTags);
        return userTags;
    }

    public Map<String, Integer> getProductAmount(int amount, Map<String, Integer> userTags) {
        Map<String, Integer> productAmount = new HashMap<String, Integer>();
        Map<String, Float> productPercent = new HashMap<String, Float>();
        int totalAmount = 0;

        if (userTags == null) {
            return null;
        }

            for (String key: userTags.keySet()) {
                totalAmount += userTags.get(key);
            }
            log.info("total amount: " + totalAmount);

            for (String key: userTags.keySet()) {
                float percent = (float)userTags.get(key) / (float)totalAmount;
                productPercent.put(key, percent);
            }
            log.info("tags percent: " + productPercent);

            for (String key: productPercent.keySet()) {
                int amo = Math.round(productPercent.get(key) * amount);
                productAmount.put(key, amo);
            }
        log.info("amount from " + amount + ": " + productAmount);

        return productAmount;
    }

    public Map<String, Integer> getUserPurchase(String userId) {
        boolean userExist = userRepo.existsById(userId);

        if (!userExist) {
            return null;
        }

        User user = getOneUser(userId);
        Map<String, Integer> userPurchase = new HashMap<String, Integer>();
        for (EventTrack track : user.getEventTracks()) {
            if (track.getProduct() != null) {
                if (userPurchase.get(track.getProduct().getProductName()) == null) {
                    userPurchase.put(track.getProduct().getProductName(), 1);
                } else {
                    int add = userPurchase.get(track.getProduct().getProductName()) + 1;
                    userPurchase.put(track.getProduct().getProductName(), add);
                }
            }
        }

        log.info("user purchases: " + userPurchase);
        return userPurchase;
    }

    public void visitUser(Map<String, String> data) {
        User user = userRepo.findByUserId(data.get("userId"));

        if (user == null) {
            createUser(data);
        } else {
            updateUserVisit(user, data.get("timestamp"));
        }
    }

    public User getUser(Map<String, String> data) {
        User user = userRepo.findByUserId(data.get("userId"));

        if (user == null) {
            return createUser(data);
        }

        return user;
    }

    public User createUser(Map<String, String> data) {
        User user = new User(data.get("userId"), data.get("timestamp"), data.get("timestamp"));

        userRepo.save(user);
        return user;
    }

    public void updateUserVisit(User user, String date) {
        // Date date = new Date();
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        user.setVisits(user.getVisits() + 1);
        user.setLastVisit(date);
        userRepo.save(user);
    }

    public User getOneUser(String userId) {
        return userRepo.getOne(userId);
    }
}
