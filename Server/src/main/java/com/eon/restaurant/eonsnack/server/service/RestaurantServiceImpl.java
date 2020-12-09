package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.exceptions.ResourceNotFoundException;
import com.eon.restaurant.eonsnack.server.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Autowired
    private CuisinesService cuisinesService;

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Page<Restaurant> findAllRestaurants(Pageable pageable) {
        Page<Restaurant> pageResult = restaurantRepository.findAll(pageable);

        return pageResult;
    }

    @Override
    public Restaurant getRestaurantById(long id) {

        Optional<Restaurant> result = restaurantRepository.findById(id);
        Restaurant restaurant = null;

        if (result.isPresent())
            restaurant = result.get();
        else
            throw new ResourceNotFoundException("not found restaurant with id: " + id);

        return restaurant;
    }

    @Override
    public Optional<Restaurant> findRestaurantByAddressId(int id) {
        return restaurantRepository.findByAddress_Id(id);
    }

    @Override
    public boolean existsById(long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }

    @Override
    public Page<Restaurant> getFilteredListOfRestaurantsByCuisinesId(List<Integer> cuisinesId) {

        List<Restaurant> filteredRestaurantList = restaurantRepository.findByCuisinesList(cuisinesId);
;
        return new PageImpl<>(filteredRestaurantList);
    }
}

