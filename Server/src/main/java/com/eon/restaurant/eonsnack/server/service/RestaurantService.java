package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    void save(Restaurant restaurant);

    Optional<Restaurant> findById(long id);

    Page<Restaurant> findAllRestaurants(Pageable pageable);

    Restaurant getRestaurantById(long id);

    Restaurant getRestaurantForMealId(long id);

    Optional<Restaurant> findRestaurantByAddressId(int id);

    boolean existsById(long restaurantId);
}
