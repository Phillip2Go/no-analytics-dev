package com.phillips.noanalyticsdev.dao.event;

import com.phillips.noanalyticsdev.model.event.EventActionProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventActionPropsRepo extends JpaRepository<EventActionProps, String> {
}
