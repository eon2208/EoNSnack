package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.MealsController;
import com.eon.restaurant.eonsnack.server.controller.RestaurantController;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant entity) {

        RestaurantModel restaurantModel = buildRestaurantModel(entity);

        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getRestaurantById(entity.getId()))
                .withSelfRel());
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getTagsForRestaurant(entity.getId()))
                .withRel("tags"));
        restaurantModel.add(linkTo(
                methodOn(MealsController.class)
                        .getMealsForRestaurant(entity.getId()))
                .withRel("meals"));
        restaurantModel.add(linkTo(
                methodOn(RestaurantController.class)
                        .getGeolocationForRestaurant(entity.getId()))
                .withRel("geolocation"));

        return restaurantModel;
    }

    public RestaurantModel buildRestaurantModel(Restaurant entity){

        return RestaurantModel.builder()
                .id(entity.getId())
                .restName(entity.getRestName())
                .restNumber(entity.getRestNumber())
                .cuisines(entity.getCuisinesList())
                .address(entity.getAddress())
                .hours(entity.getHours())
                .build();
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }
}

