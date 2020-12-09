package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import com.eon.restaurant.eonsnack.server.model.assembler.MealModelAssembler;
import com.eon.restaurant.eonsnack.server.service.MealService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/api/meals")
public class MealsController {

    final MealService mealService;
    final MealModelAssembler mealModelAssembler;

    private final PagedResourcesAssembler<Meal> pagedResourcesAssembler;

    public MealsController(MealService mealService, MealModelAssembler mealModelAssembler, PagedResourcesAssembler<Meal> pagedResourcesAssembler) {
        this.mealService = mealService;
        this.mealModelAssembler = mealModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }



    @GetMapping("meals/{restId}/{tagId}")
    public ResponseEntity<CollectionModel<MealModel>> getMealsForRestaurantAndTag(
            @PathVariable("restId") long restId, @PathVariable("tagId") int tagId) {
        List<Meal> mealList = mealService.findAllByRestaurantIdAndTagsId(restId, tagId);

        return new ResponseEntity<>(mealModelAssembler.toCollectionModel(mealList), HttpStatus.OK);
    }

    @GetMapping("/mealss/{id}")
    public ResponseEntity<MealModel> getMealsById(@PathVariable("id") long id) {

        return mealService.findById(id)
                .map(mealModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
