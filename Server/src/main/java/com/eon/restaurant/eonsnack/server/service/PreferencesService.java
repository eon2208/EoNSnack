package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.User;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;

public interface PreferencesService {

    void saveUserPreferences(PreferencesRequest preferencesRequest, User user);

    void saveRestaurant(Long id, User user);
}
