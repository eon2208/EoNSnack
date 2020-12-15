package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Preferences;
import com.eon.restaurant.eonsnack.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    boolean existsByUser(User user);
}
