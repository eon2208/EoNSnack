package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;
import com.eon.restaurant.eonsnack.server.repository.PreferencesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Set<Cuisines> cuisinesSet = new HashSet<>(cuisinesService.findAllByIdList(preferencesRequest.getTagsId()));

        Preferences preferences = null;

        else(preferencesRepository.existsByUser(user)){
            Preferences userPreferences = user.getPreferences();

            userPreferences.setCuisines(cuisinesSet);
            userPreferences.setTags();


        }
        if{
            preferences = new Preferences();
        }


        preferencesRepository.save(preferences)
    }

    @Override
    public void saveRestaurant(Long id, User user) {
    }
}
