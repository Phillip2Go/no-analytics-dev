package com.phillips.noanalyticsdev.service.user;

import com.phillips.noanalyticsdev.dao.user.UserRepo;
import com.phillips.noanalyticsdev.model.user.User;
import com.phillips.noanalyticsdev.model.event.EventTrack;
import com.phillips.noanalyticsdev.service.cms.CmsService;
import com.phillips.noanalyticsdev.util.ProductItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private CmsService cmsService;
    @Autowired
    private UserRepo userRepo;
    private static final int PRODUCT_COUNT = 5;
    private static final int KLICK_COUNT = 1;
    private static final int NUMB_NULL = 0;
    private static final String USER_ID = "userId";
    private static final String TIMESTAMP = "timestamp";

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
                    userTags.put(track.getTag(), PRODUCT_COUNT);
                } else {
                    int add = userTags.get(track.getTag()) + PRODUCT_COUNT;
                    userTags.put(track.getTag(), add);
                }
            } else {
                if (userTags.get(track.getTag()) == null) {
                    userTags.put(track.getTag(), KLICK_COUNT);
                } else {
                    int add = userTags.get(track.getTag()) + KLICK_COUNT;
                    userTags.put(track.getTag(), add);
                }
            }
        }

        log.info("total user tags: " + userTags);
        return userTags;
    }

    public Map<String, Integer> getUserTagsDesc(String userId) {
        Map<String, Integer> userTags = getUserTags(userId);
        Map<String, Integer> reverseSortedUserTags = new LinkedHashMap<>();

        userTags.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedUserTags.put(x.getKey(), x.getValue()));

        return reverseSortedUserTags;
    }

    public String getMostUsedTag(String userId) {
        Map<String, Integer> reverseSortedUserTags = getUserTagsDesc(userId);
        String mostUsedTag = reverseSortedUserTags.keySet().iterator().next();

        return mostUsedTag;
    }

    public List<String> getUsedTagsDesc(String userId) {
        Map<String, Integer> reverseSortedUserTags = getUserTagsDesc(userId);
        List<String> tags = new ArrayList<>(reverseSortedUserTags.keySet());

        return tags;
    }

    public List<ProductItem> getUserProducts(int amount, String userId) {
        Map<String, Integer> userTags = getUserTags(userId);
        Map<String, Integer> productAmount = calcProductAmount(amount, userTags);
        Map<String, Integer> userPurchase = getUserOrder(userId);

        if (!userTrackExist(userId) || productAmount == null) {
            return null;
        }

        List<ProductItem> items;
        List<ProductItem> userProducts = new ArrayList<>();
        for (String key: productAmount.keySet()) {
            if (productAmount.get(key) > NUMB_NULL) {
                items = cmsService.getProductsByTag(key);
                Collections.shuffle(items);
                userProducts.addAll(items.subList(NUMB_NULL, productAmount.get(key)));
            }
        }
        Collections.shuffle(userProducts);
        return userProducts;
    }

    public Map<String, Integer> calcProductAmount(int amount, Map<String, Integer> userTags) {
        Map<String, Integer> productAmount = new HashMap<String, Integer>();
        Map<String, Float> productPercent = new HashMap<String, Float>();
        int totalAmount = NUMB_NULL;

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

    public Map<String, Integer> getUserOrder(String userId) {
        boolean userExist = userRepo.existsById(userId);

        if (!userExist) {
            return null;
        }

        User user = getOneUser(userId);
        Map<String, Integer> userOrder = new HashMap<String, Integer>();
        for (EventTrack track : user.getEventTracks()) {
            if (track.getProduct() != null) {
                if (userOrder.get(track.getProduct().getProductName()) == null) {
                    userOrder.put(track.getProduct().getProductName(), 1);
                } else {
                    int add = userOrder.get(track.getProduct().getProductName()) + 1;
                    userOrder.put(track.getProduct().getProductName(), add);
                }
            }
        }

        log.info("user purchases: " + userOrder);
        return userOrder;
    }

    public boolean userTrackExist(String userId) {
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

    public void checkUser(Map<String, String> data) {
        User user = userRepo.findByUserId(data.get(USER_ID));

        if (user == null) {
            createUser(data);
        } else {
            updateUserVisit(user, data.get(TIMESTAMP));
        }
    }

    public User getUser(Map<String, String> data) {
        User user = userRepo.findByUserId(data.get(USER_ID));

        if (user == null) {
            return createUser(data);
        }

        return user;
    }

    public User createUser(Map<String, String> data) {
        User user = new User(data.get(USER_ID), data.get(TIMESTAMP), data.get(TIMESTAMP));

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
