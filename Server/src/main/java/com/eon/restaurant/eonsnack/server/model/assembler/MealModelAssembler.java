package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.MealsController;
import com.eon.restaurant.eonsnack.server.controller.RestaurantController;
import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.MealModel;

import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MealModelAssembler extends RepresentationModelAssemblerSupport<Meal, MealModel> {

        public MealModelAssembler() {
            super(MealsController.class, MealModel.class);
        }

        @Override
        public MealModel toModel(Meal entity) {

            MealModel mealModel = buildMealModel(entity);

            mealModel.add(linkTo(
                    methodOn(RestaurantController.class)
                            .getMealsForRestaurant(entity.getId()))
                    .withRel("restaurant"));

            return mealModel;
        }

    public MealModel buildMealModel(Meal entity) {

        return MealModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .restaurant(entity.getRestaurant())
                .tag(entity.getTags())
                .build();
    }

        @Override
        public CollectionModel<MealModel> toCollectionModel(Iterable<? extends Meal> entities) {

            return StreamSupport
                    .stream(entities.spliterator(), false)
                    .map(this::toModel)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
        }

}
