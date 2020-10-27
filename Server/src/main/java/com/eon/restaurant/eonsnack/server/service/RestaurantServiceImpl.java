package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.client.RestaurantMenuClient;
import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.exception.RestaurantNotFoundException;
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
    private final CuisinesService cuisinesService;
    private final TagsService tagsService;
    private final RestaurantMenuClient restaurantMenuClient;

    private Restaurant restaurantHelper;
    List<Meal> mealList;


    Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    public RestaurantServiceImpl(RestaurantMenuClient restaurantMenuClient,
                                 RestaurantRepository restaurantRepository,
                                 CuisinesService cuisinesService,
                                 TagsService tagsService) {

        this.restaurantRepository = restaurantRepository;
        this.restaurantMenuClient = restaurantMenuClient;
        this.cuisinesService = cuisinesService;
        this.tagsService = tagsService;
    }

    @Override
    public void saveRestaurantFromJSON(long id) {

        RestaurantJsonItem restaurantJsonItem = restaurantMenuClient.getJSONRestaurantWithId(id);
        if (restaurantJsonItem != null && !restaurantRepository.existsById(id)) {

            this.restaurantHelper = new Restaurant();
            saveProperties(restaurantJsonItem);
            logger.info("All properties saved");

            restaurantRepository.save(restaurantHelper);
            logger.info("Saved !!! restaurant with id :" + id);
        }
        else
            logger.info("This restaurant already exists");
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
    public Optional<Restaurant> findRestaurantByAddressId(int id)
    {
        return restaurantRepository.findByAddress_Id(id);
    }

    @Override
    public boolean existsById(long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }

    private void saveProperties(RestaurantJsonItem restaurantJsonItem) {
        saveBasics(restaurantJsonItem);
        saveAddress(restaurantJsonItem.getResult().getJsonAddress());
        saveTags(restaurantJsonItem.getResult().getMenuTags());
        saveMeals(restaurantJsonItem.getResult().getJsonMenus());
        saveCuisines(restaurantJsonItem.getResult().getCuisines());
        saveGeolocation(restaurantJsonItem.getResult().getJsonGeo());
    }

    private void saveMeals(List<JsonMenu> jsonMenus) {

        if (!jsonMenus.isEmpty()) {
            createMealList(jsonMenus);
        }

        logger.info("Meals saved");
    }

    private void createMealList(List<JsonMenu> jsonMenus) {
        mealList = new ArrayList<>();
        for (JsonMenu jsonMenu : jsonMenus) {
            for (MenuSection menuSection : jsonMenu.getMenuSections()) {
                Tags tags = gatTag(menuSection.getSectionName());
                addMealToListOfMeals(menuSection, tags);
            }
        }

        restaurantHelper.setMeals(mealList);
    }

    private void addMealToListOfMeals(MenuSection menuSection, Tags tags) {
        for (JsonMeal jsonMeal : menuSection.getMenuItems()) {

            Meal meal = Meal.builder()
                    .description(jsonMeal.getDescription())
                    .name(jsonMeal.getName())
                    .price(jsonMeal.getPrice())
                    .restaurant(this.restaurantHelper)
                    .tags(tags)
                    .build();

            mealList.add(meal);
        }
    }

    private Tags gatTag(String tagName) {
        Tags tags = new Tags();
        if(tagsService.existsByName(tagName))
            tags = tagsService.getByName(tagName);

        return tags;
    }

    private void saveGeolocation(JsonGeo jsonGeo) {
        Geolocation geolocation = Geolocation.builder()
                .lat(jsonGeo.getLat())
                .lon(jsonGeo.getLon())
                .build();

        this.restaurantHelper.setGeolocation(geolocation);
        logger.info("Geolocation saved");
    }

    private void saveBasics(RestaurantJsonItem restaurantJsonItem) {
        this.restaurantHelper.setId(restaurantJsonItem.getResult().getRestaurantId());
        this.restaurantHelper.setHours(restaurantJsonItem.getResult().getHours());
        this.restaurantHelper.setRestName(restaurantJsonItem.getResult().getRestaurantName());
        this.restaurantHelper.setRestNumber(restaurantJsonItem.getResult().getRestaurantPhone());
    }

    private void saveCuisines(List<String> jsonCuisines) {
        if (!jsonCuisines.isEmpty()) {
            List<Cuisines> cuisinesList = new ArrayList<>();

            for (String cuisineName : jsonCuisines) {
                addCuisineToList(cuisinesList, cuisineName);
            }

            this.restaurantHelper.setCuisinesList(cuisinesList);
            logger.info("Cuisines saved");
        }
    }

    private void addCuisineToList(List<Cuisines> cuisinesList, String cuisineName) {
        if (cuisinesService.existsByName(cuisineName)) {
            Cuisines currentlyExisting = cuisinesService.getByName(cuisineName);
            cuisinesList.add(currentlyExisting);
        } else {
            Cuisines cuisines = new Cuisines();
            cuisines.setName(cuisineName);
            cuisinesList.add(cuisines);
        }
    }

    private void saveTags(List<String> jsonTags) {
        if (!jsonTags.isEmpty()) {
            List<Tags> tagsList = new ArrayList<>();

            for (String tagName : jsonTags) {
                addTagToList(tagsList, tagName);
            }

            this.restaurantHelper.setTagsList(tagsList);
            logger.info("tags saved");
        }
    }

    private void addTagToList(List<Tags> tagsList, String tagName) {
        Tags tag;
        String tagToLower = tagName.toLowerCase();
        if (tagsService.existsByName(tagToLower)) {
            tag =  tagsService.getByName(tagToLower);
        } else {
            tag = new Tags();
            tag.setName(tagToLower);
            tagsService.save(tag);
        }

        tagsList.add(tag);
    }

    private void saveAddress(JsonAddress jsonAddress) {

        Address address = Address.builder()
                .city(jsonAddress.getCity())
                .formatted(jsonAddress.getFormatted())
                .postal_code(jsonAddress.getPostalCode())
                .state(jsonAddress.getState())
                .street(jsonAddress.getStreet())
                .build();

        this.restaurantHelper.setAddress(address);
        logger.info("Address saved");
    }
}
