package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.AddressModel;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import com.eon.restaurant.eonsnack.server.model.assembler.MealModelAssembler;
import com.eon.restaurant.eonsnack.server.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/api")
public class MealsController {

    final MealService mealService;
    final MealModelAssembler mealModelAssembler;

    private final PagedResourcesAssembler<Meal> pagedResourcesAssembler;

    public MealsController(MealService mealService, MealModelAssembler mealModelAssembler, PagedResourcesAssembler<Meal> pagedResourcesAssembler) {
        this.mealService = mealService;
        this.mealModelAssembler = mealModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/meals")
    public ResponseEntity<PagedModel<MealModel>> getAllMeals(@RequestParam(value = "page", defaultValue = "0", name = "page") int page,
                                                             @RequestParam(value = "size", defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Meal> mealPage = mealService.findAll(pageable);

        PagedModel<MealModel> collModel = pagedResourcesAssembler.toModel(mealPage, mealModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
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

    @GetMapping("/{id}/meals")
    public ResponseEntity<CollectionModel<MealModel>> getMealsForRestaurant(@PathVariable("id") long id) {
        List<Meal> mealList = mealService.findAllByRestaurantId(id);

        return new ResponseEntity<>(mealModelAssembler.toCollectionModel(mealList), HttpStatus.OK);
    }

}
