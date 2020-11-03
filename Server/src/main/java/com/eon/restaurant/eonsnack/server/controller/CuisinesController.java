package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.model.CuisineModel;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import com.eon.restaurant.eonsnack.server.model.assembler.CuisinesModelAssembler;
import com.eon.restaurant.eonsnack.server.service.CuisinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RepositoryRestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping
public class CuisinesController {

    @Autowired
    private CuisinesService cuisinesService;
    @Autowired
    private CuisinesModelAssembler cuisinesModelAssembler;

    @GetMapping("/api/cuisines")
    public ResponseEntity<CollectionModel<CuisineModel>> getMealsForRestaurantAndTag(){

        List<Cuisines> cuisinesList = cuisinesService.findAll();

        return new ResponseEntity<>(cuisinesModelAssembler.toCollectionModel(cuisinesList), HttpStatus.OK);
    }
}
