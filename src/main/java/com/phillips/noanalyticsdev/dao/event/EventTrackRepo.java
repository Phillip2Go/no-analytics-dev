package com.phillips.noanalyticsdev.dao.event;

import com.phillips.noanalyticsdev.model.event.EventTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTrackRepo extends JpaRepository<EventTrack, Long> {
}
