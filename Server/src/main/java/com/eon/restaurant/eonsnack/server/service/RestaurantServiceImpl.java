package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.client.RestaurantMenuClient;
import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.exception.RestaurantNotFoundException;
import com.eon.restaurant.eonsnack.server.repository.MealRepository;
import com.eon.restaurant.eonsnack.server.repository.RestaurantRepository;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MealRepository mealRepository;


    Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(long id) {

        Optional<Restaurant> result = restaurantRepository.findById(id);
        Restaurant restaurant = null;

        if (result.isPresent())
            restaurant = result.get();
        else throw new RestaurantNotFoundException(id);

        return restaurant;
    }

    @Override
    public Restaurant getRestaurantForMealId(long id) {
        Optional<Meal> result = mealRepository.findById(id);

        Meal meal = null;
        if(result.isPresent())
            meal = result.get();

        return meal.getRestaurant();
    }

    @Override
    public Optional<Restaurant> findRestaurantByAddressId(int id) {
        return restaurantRepository.findByAddress_Id(id);
    }

    @Override
    public boolean existsById(long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }
}

