package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.HalLinksController.GeolocationController;
import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import com.eon.restaurant.eonsnack.server.model.GeolocationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GeolocationModelAssembler extends RepresentationModelAssemblerSupport<Geolocation, GeolocationModel> {

    public GeolocationModelAssembler() {
        super(WebController.class, GeolocationModel.class);
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
}
