package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.*;
import com.eon.restaurant.eonsnack.server.model.*;
import com.eon.restaurant.eonsnack.server.model.assembler.*;
import com.eon.restaurant.eonsnack.server.service.AddressService;
import com.eon.restaurant.eonsnack.server.service.CuisinesService;
import com.eon.restaurant.eonsnack.server.service.MealService;
import com.eon.restaurant.eonsnack.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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
    private PagedResourcesAssembler<Restaurant> pagedResourcesAssembler;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MealService mealService;

    @Autowired
    private CuisinesService cuisinesService;

    @GetMapping("/list")
    public ResponseEntity<PagedModel<RestaurantModel>> getAllRestaurants(@RequestParam(value = "page", defaultValue = "0", name = "page") int page,
                                                                         @RequestParam(value = "size", defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Restaurant> restaurants = restaurantService.findAllRestaurants(pageable);

        PagedModel<RestaurantModel> collModel = pagedResourcesAssembler.toModel(restaurants, restaurantModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable("id") long id) {

        return restaurantService.findById(id)
                .map(restaurantModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("not found restaurant with id: " + id));
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<AddressModel> getAddressForRestaurant(@PathVariable("id") long id) {
        Address address = addressService.findByRestaurantId(id);

        return new ResponseEntity<>(addressModelAssembler.toModel(address), HttpStatus.OK);
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


    @GetMapping("/{id}/geolocations")
    public ResponseEntity<GeolocationModel> getGeolocationForRestaurant(@PathVariable("id") long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        Geolocation geolocation = restaurant.getGeolocation();

        return new ResponseEntity<>(geolocationModelAssembler.toModel(geolocation), HttpStatus.OK);
    }

    @GetMapping("/{id}/restaurants")
    public ResponseEntity<RestaurantModel> getRestaurantByAddressId(@PathVariable("id") int id) {

        return restaurantService.findRestaurantByAddressId(id)
                .map(restaurantModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter/cuisines")
    public ResponseEntity<PagedModel<RestaurantModel>> getRestaurantsByCuisinesFilter(@RequestParam List<Integer> cuisinesId) {

        Page<Restaurant> restaurants = restaurantService.getFilteredListOfRestaurantsByCuisinesId(cuisinesId);

        PagedModel<RestaurantModel> collModel = pagedResourcesAssembler.toModel(restaurants, restaurantModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }
}
