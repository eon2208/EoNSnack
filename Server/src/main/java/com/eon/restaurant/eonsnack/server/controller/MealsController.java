package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import com.eon.restaurant.eonsnack.server.model.assembler.MealModelAssembler;
import com.eon.restaurant.eonsnack.server.service.MealService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
@RequestMapping("/api")
public class MealsController {

    final MealService mealService;
    final MealModelAssembler mealModelAssembler;

    public MealsController(MealService mealService, MealModelAssembler mealModelAssembler) {
        this.mealService = mealService;
        this.mealModelAssembler = mealModelAssembler;
    }

    @GetMapping("meals/{restId}/{tagId}")
    public ResponseEntity<CollectionModel<MealModel>> getMealsForRestaurantAndTag(
            @PathVariable("restId") long restId, @PathVariable("tagId") int tagId){
        List<Meal> mealList = mealService.findAllByRestaurantIdAndTagsId(restId,tagId);

        return new ResponseEntity<>(mealModelAssembler.toCollectionModel(mealList), HttpStatus.OK);
    }
}
