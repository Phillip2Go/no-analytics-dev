package com.phillips.noanalyticsdev;

import com.phillips.noanalyticsdev.dao.user.UserRepo;
import com.phillips.noanalyticsdev.dao.event.EventActionPropsRepo;
import com.phillips.noanalyticsdev.dao.event.EventRepo;
import com.phillips.noanalyticsdev.dao.event.EventTrackRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo, EventRepo eventRepo, EventTrackRepo eventTrackRepo, EventActionPropsRepo eventActionPropsRepo) {
        return args -> {
           // log.info("Preloading DB: " + userRepo.save(new User("Phillip Mai")));
           // log.info("Preloading DB: " + eventRepo.save(new Event("Event1", "click", 1)));
           // log.info("Preloading DB: " + eventTrackRepo.save(new EventTrack("click", "User1", "tag1")));
           // log.info("Preloading DB: " + eventActionPropsRepo.save(new EventActionProps("EventActionProps1")));
        };
    }
}
