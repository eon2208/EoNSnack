package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.client.RestaurantMenuClient;
import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JsonToEntityImpl implements JsonToEntity {

    private Restaurant restaurantHelper;
    Set<Meal> mealList;

    Logger logger = LoggerFactory.getLogger(JsonToEntityImpl.class);

    private final RestaurantService restaurantService;

    private final TagsService tagsService;

    private final CuisinesService cuisinesService;

    final
    RestaurantMenuClient restaurantMenuClient;

    public JsonToEntityImpl(RestaurantService restaurantService, TagsService tagsService, CuisinesService cuisinesService, RestaurantMenuClient restaurantMenuClient) {
        this.restaurantService = restaurantService;
        this.tagsService = tagsService;
        this.cuisinesService = cuisinesService;
        this.restaurantMenuClient = restaurantMenuClient;
    }

    public void saveRestaurantFromJSON(long id) {

        RestaurantJsonItem restaurantJsonItem = restaurantMenuClient.getJSONRestaurantWithId(id);
        if (restaurantJsonItem != null && !restaurantService.existsById(id)) {

            this.restaurantHelper = new Restaurant();
            saveProperties(restaurantJsonItem);
            logger.info("All properties saved");

            restaurantService.save(restaurantHelper);
            logger.info("Saved !!! restaurant with id :" + id);
        }
        else
            logger.info("This restaurant already exists");
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
        mealList = new HashSet<>();
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
            Set<Cuisines> cuisinesList = new HashSet<>();

            for (String cuisineName : jsonCuisines) {
                addCuisineToList(cuisinesList, cuisineName);
            }

            this.restaurantHelper.setCuisinesList(cuisinesList);
            logger.info("Cuisines saved");
        }
    }

    private void addCuisineToList(Set<Cuisines> cuisinesList, String cuisineName) {
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
            Set<Tags> tagsSet = new HashSet<>();

            for (String tagName : jsonTags) {
                addTagToList(tagsSet, tagName);
            }

            this.restaurantHelper.setTagsList(tagsSet);
            logger.info("tags saved");
        }
    }

    private void addTagToList(Set<Tags> tagsList, String tagName) {
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
