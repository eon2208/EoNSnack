package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.User;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import com.eon.restaurant.eonsnack.server.model.PreferencesModel;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import com.eon.restaurant.eonsnack.server.model.assembler.PreferencesModelAssembler;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;
import com.eon.restaurant.eonsnack.server.service.PreferencesService;
import com.eon.restaurant.eonsnack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/preferences", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreferencesController {

    private final PreferencesService preferencesService;

    private final UserService userService;

    private final PreferencesModelAssembler preferencesModelAssembler;


    public PreferencesController(PreferencesService preferencesService, UserService userService, PreferencesModelAssembler preferencesModelAssembler) {
        this.preferencesService = preferencesService;
        this.userService = userService;
        this.preferencesModelAssembler = preferencesModelAssembler;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public void savePreferences(@RequestBody PreferencesRequest preferencesRequest, Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        preferencesService.saveUserPreferences(preferencesRequest, user);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addRestaurant")
    public void addRestaurantWithId(@RequestParam("restaurantId") long restaurantId, Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        preferencesService.saveRestaurant(restaurantId, user);
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    ResponseEntity<PreferencesModel> getPreferencesForLoggedUser(Authentication authentication){

        User user = userService.findByUsername(authentication.getName());

        return preferencesService.findByUser(user)
                .map(preferencesModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("did not found preferences for this user"));
    }

    //get user for preferences
    //get restaurants for preferences
    //get tags for preferences
    //get cuisines for preferences
}
