package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.User;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;

public interface UserService {

    User findByUsername(String userName);

}
