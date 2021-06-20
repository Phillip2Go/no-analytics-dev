package com.phillips.noanalyticsdev.dao.event;

import com.phillips.noanalyticsdev.model.event.EventTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTrackRepo extends JpaRepository<EventTrack, Long> {
    List<EventTrack> findAllByProduct_ProductTag(String tag);
    List<EventTrack> findAllByProduct_NotNull();
}
