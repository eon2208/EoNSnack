package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.RestaurantController;
import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    public RestaurantModelAssembler() {
        super(WebController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant entity) {

        RestaurantModel restaurantModel = instantiateModel(entity);

        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getRestaurantById(entity.getId()))
                .withSelfRel());
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getRestaurantById(entity.getId()))
                .withRel("restaurant"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getTagsForRestaurant(entity.getId()))
                .withRel("tags"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getMealsForRestaurant(entity.getId()))
                .withRel("meals"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getAddressForRestaurant(entity.getId()))
                .withRel("address"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getGeolocationForRestaurant(entity.getId()))
                .withRel("geolocation"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getCuisinesForRestaurant(entity.getId()))
                .withRel("cuisines"));

        restaurantModel.setId(entity.getId());
        restaurantModel.setRestName(entity.getRestName());
        restaurantModel.setRestNumber(entity.getRestNumber());
        restaurantModel.setHours(entity.getHours());
        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {

        CollectionModel<RestaurantModel> restaurantModels = super.toCollectionModel(entities);

        restaurantModels.add(linkTo(methodOn(RestaurantController.class).getAllRestaurants()).withSelfRel());

        return restaurantModels;
    }

}

