package com.phillips.noanalyticsdev.dao;

import com.phillips.noanalyticsdev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByUserId(String userId);
}
