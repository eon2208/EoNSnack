package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Meal;

import java.util.List;
import java.util.Optional;

public interface MealService {

    List<Meal> findAll();

    Optional<Meal> findById(long id);

    List<Meal> findAllByRestaurantId(long id);
}
