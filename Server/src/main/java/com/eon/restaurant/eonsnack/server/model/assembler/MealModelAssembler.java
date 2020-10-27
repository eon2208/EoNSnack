package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.HalLinksController.RestaurantController;
import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.model.MealModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MealModelAssembler extends RepresentationModelAssemblerSupport<Meal, MealModel> {

        public MealModelAssembler() {
            super(WebController.class, MealModel.class);
        }

        @Override
        public MealModel toModel(Meal entity) {

            MealModel mealModel = instantiateModel(entity);

            mealModel.add(linkTo(
                    methodOn(WebController.class)
                            .getMealsById(entity.getId()))
                    .withSelfRel());
            mealModel.add(linkTo(
                    methodOn(RestaurantController.class)
                            .getMealsForRestaurant(entity.getId()))
                    .withRel("restaurant"));

            mealModel.setId(entity.getId());
            mealModel.setDescription(entity.getDescription());
            mealModel.setName(entity.getName());
            mealModel.setPrice(entity.getPrice());
            return mealModel;
        }

        @Override
        public CollectionModel<MealModel> toCollectionModel(Iterable<? extends Meal> entities) {

            CollectionModel<MealModel> restaurantModels = super.toCollectionModel(entities);

            restaurantModels.add(linkTo(methodOn(WebController.class).getAllMeals()).withSelfRel());

            return restaurantModels;
        }

}
