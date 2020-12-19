package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.repository.CuisinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CuisinesServiceImpl implements CuisinesService {

    @Autowired
    private CuisinesRepository cuisinesRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Cuisines getByName(String name) {

        return cuisinesRepository.getByName(name);
    }

    @Override
    public Boolean existsByName(String name) {

        return cuisinesRepository.existsByName(name);
    }

    @Override
    public Optional<Cuisines> findById(int id) {
        return cuisinesRepository.findById(id);
    }

    @Override
    public List<Cuisines> findAllByIdList(Set<Integer> cuisinesId) {

        return cuisinesRepository.findAllByIdList(cuisinesId);
    }

    @Override
    public Page<Cuisines> findAll(Pageable pageable) {
        return cuisinesRepository.findAll(Pageable.unpaged());
    }

    @Override
    public List<Cuisines> findAllByRestaurantId(long id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);

        return cuisinesRepository.findByRestaurant(restaurant);
    }
}
