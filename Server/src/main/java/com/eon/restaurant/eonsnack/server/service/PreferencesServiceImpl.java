package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;
import com.eon.restaurant.eonsnack.server.repository.PreferencesRepository;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PreferencesServiceImpl implements PreferencesService {

    private final TagsService tagsService;

    private final CuisinesService cuisinesService;

    private final RestaurantService restaurantService;

    private final PreferencesRepository preferencesRepository;

    public PreferencesServiceImpl(PreferencesRepository preferencesRepository, TagsService tagsService, CuisinesService cuisinesService, RestaurantService restaurantService) {
        this.preferencesRepository = preferencesRepository;
        this.tagsService = tagsService;
        this.cuisinesService = cuisinesService;
        this.restaurantService = restaurantService;
    }

    @Override
    public void saveUserPreferences(PreferencesRequest preferencesRequest, User user) {

        Set<Tags> tagsSet = new HashSet<>(tagsService.findAllByIdList(preferencesRequest.getTagsId()));
        Set<Cuisines> cuisinesSet = new HashSet<>(cuisinesService.findAllByIdList(preferencesRequest.getCuisinesId()));

        Preferences preferences = getPreferencesForCurrentUser(user);

        preferences.setCuisines(cuisinesSet);
        preferences.setTags(tagsSet);

        preferencesRepository.save(preferences);

    }

    private Preferences getPreferencesForCurrentUser(User user) {
        Preferences preferences;
        if (preferencesRepository.existsByUser(user)) {
            preferences = user.getPreferences();
        } else {
            preferences = new Preferences();
            preferences.setUser(user);
        }
        return preferences;
    }

    @Override
    public void saveRestaurant(Long id, User user) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);

        Preferences preferences = getPreferencesForCurrentUser(user);
        preferences.getRestaurants().add(restaurant);

        preferencesRepository.save(preferences);
    }

    @Override
    public Optional<Preferences> findByUser(User user) {
        return preferencesRepository.findByUser(user);
    }
}
