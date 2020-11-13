package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.GeolocationController;
import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.model.GeolocationModel;
import com.eon.restaurant.eonsnack.server.model.RestaurantModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GeolocationModelAssembler extends RepresentationModelAssemblerSupport<Geolocation, GeolocationModel> {

    public GeolocationModelAssembler() {
        super(GeolocationController.class, GeolocationModel.class);
    }

    @Override
    public GeolocationModel toModel(Geolocation entity) {

        GeolocationModel geolocationModel = instantiateModel(entity);

        geolocationModel.add(linkTo(
                methodOn(GeolocationController.class)
                .getGeolocationById(entity.getId()))
                .withSelfRel());

        geolocationModel.setId(entity.getId());
        geolocationModel.setLat(entity.getLat());
        geolocationModel.setLon(entity.getLon());
        return geolocationModel;
    }

    @Override
    public CollectionModel<GeolocationModel> toCollectionModel(Iterable<? extends Geolocation> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }
}
