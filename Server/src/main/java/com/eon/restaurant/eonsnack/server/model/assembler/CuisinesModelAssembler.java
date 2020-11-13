package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.CuisinesController;
import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.model.CuisineModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CuisinesModelAssembler extends RepresentationModelAssemblerSupport<Cuisines, CuisineModel> {

    public CuisinesModelAssembler() {
        super(CuisinesController.class, CuisineModel.class);
    }

    @Override
    public CuisineModel toModel(Cuisines entity) {

        CuisineModel cuisinesModel = instantiateModel(entity);

        cuisinesModel.add(linkTo(
                methodOn(CuisinesController.class)
                        .getCuisinesById(entity.getId()))
                .withSelfRel());

        cuisinesModel.setId(entity.getId());
        cuisinesModel.setName(entity.getName());
        return cuisinesModel;
    }

    @Override
    public CollectionModel<CuisineModel> toCollectionModel(Iterable<? extends Cuisines> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }



}
