package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    void saveRestaurantFromJSON(long id);

    Optional<Restaurant> findById(long id);

    List<Restaurant> findAll();

    Restaurant getRestaurantById(long id);

    Optional<Restaurant> findRestaurantByAddressId(int id);

    boolean existsById(long restaurantId);
}
