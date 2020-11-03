package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.model.*;
import com.eon.restaurant.eonsnack.server.model.assembler.*;
import com.eon.restaurant.eonsnack.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RepositoryRestController
@RequestMapping("/hat")
public class RestaurantController {

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private MealModelAssembler mealModelAssembler;

    @Autowired
    private AddressModelAssembler addressModelAssembler;

    @Autowired
    private TagModelAssembler tagModelAssembler;

    @Autowired
    private CuisinesModelAssembler cuisinesModelAssembler;

    @Autowired
    private GeolocationModelAssembler geolocationModelAssembler;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MealService mealService;


    @GetMapping("/restaurants")
    public ResponseEntity<CollectionModel<RestaurantModel>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAll();

        return new ResponseEntity<>(restaurantModelAssembler.toCollectionModel(restaurants), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable("id") long id) {

        return restaurantService.findById(id)
                .map(restaurantModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/meals")
    public ResponseEntity<CollectionModel<MealModel>> getMealsForRestaurant(@PathVariable("id") long id) {
        List<Meal> mealList = mealService.findAllByRestaurantId(id);

        return new ResponseEntity<>(mealModelAssembler.toCollectionModel(mealList), HttpStatus.OK);
    }

   @GetMapping("/{id}/address")
    public ResponseEntity<AddressModel> getAddressForRestaurant(@PathVariable("id") long id) {
        Address address = addressService.findByRestaurantId(id);

        return new ResponseEntity<>(addressModelAssembler.toModel(address),HttpStatus.OK);
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<CollectionModel<TagModel>> getTagsForRestaurant(@PathVariable("id") long id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<Tags> tagsList = restaurant.getTagsList();


        return new ResponseEntity<>(tagModelAssembler.toCollectionModel(tagsList), HttpStatus.OK);
    }

    @GetMapping("/{id}/cuisines")
    public ResponseEntity<CollectionModel<CuisineModel>> getCuisinesForRestaurant(@PathVariable("id") long id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<Cuisines> cuisinesList = restaurant.getCuisinesList();

        return new ResponseEntity<>(cuisinesModelAssembler.toCollectionModel(cuisinesList), HttpStatus.OK);
    }

    @GetMapping("/{id}/geolocation")
    public ResponseEntity<GeolocationModel> getGeolocationForRestaurant(@PathVariable("id") long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        Geolocation geolocation = restaurant.getGeolocation();

        return new ResponseEntity<>(geolocationModelAssembler.toModel(geolocation),HttpStatus.OK);
    }

    @GetMapping("/{id}/restaurant")
    public ResponseEntity<RestaurantModel> getRestaurantByAddressId(@PathVariable("id") int id) {

        return restaurantService.findRestaurantByAddressId(id)
                .map(restaurantModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
