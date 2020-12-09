package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.service.JsonToEntityImpl;
import com.eon.restaurant.eonsnack.server.client.RestaurantMenuClient;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantList.RestaurantsList;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.RestaurantJsonItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class GetFromRapidApi {

    private final RestaurantMenuClient restaurantMenuClient;

    private final JsonToEntityImpl jsonToEntity;

    @Autowired
    public GetFromRapidApi(RestaurantMenuClient restaurantMenuClient, JsonToEntityImpl jsonToEntity) {
        this.restaurantMenuClient = restaurantMenuClient;
        this.jsonToEntity = jsonToEntity;
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantJsonItem> menuItem(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(restaurantMenuClient.getJSONRestaurantWithId(id));
    }

    @GetMapping("/add/{id}")
    public ResponseEntity<String> saveRestaurant(@PathVariable("id") long id) {
        jsonToEntity.saveRestaurantFromJSON(id);

        return new ResponseEntity<>("Restaurant added : " + id, HttpStatus.OK);
    }

    @GetMapping("/list/{page}")
    public ResponseEntity<RestaurantsList> restaurantsListResponseEntity(@PathVariable("page") int page) {
        return ResponseEntity.ok().body(restaurantMenuClient.getJsonRestaurantsList(page));
    }

    @GetMapping("/list/{page}/add")
    public ResponseEntity<String> addListOfRestaurants(@PathVariable("page") int page) {
        restaurantMenuClient.saveRestaurantsPage(page);

        return new ResponseEntity<>("Page Added!", HttpStatus.OK);
    }


}
