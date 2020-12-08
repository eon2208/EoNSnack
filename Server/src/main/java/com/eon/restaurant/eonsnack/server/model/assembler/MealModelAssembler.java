package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.MealsController;
import com.eon.restaurant.eonsnack.server.controller.RestaurantController;
import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.model.MealModel;

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

            MealModel mealModel = instantiateModel(entity);

            mealModel.add(linkTo(
                    methodOn(RestaurantController.class)
                            .getMealsForRestaurant(entity.getId()))
                    .withRel("restaurant"));

            mealModel.setId(entity.getId());
            mealModel.setDescription(entity.getDescription());
            mealModel.setName(entity.getName());
            mealModel.setPrice(entity.getPrice());
            mealModel.setTag(entity.getTags());
            return mealModel;
        }

        @Override
        public CollectionModel<MealModel> toCollectionModel(Iterable<? extends Meal> entities) {

            return StreamSupport
                    .stream(entities.spliterator(), false)
                    .map(this::toModel)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
        }

}
