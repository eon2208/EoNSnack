package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.model.CuisineModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CuisinesModelAssembler extends RepresentationModelAssemblerSupport<Cuisines, CuisineModel> {

    public CuisinesModelAssembler() {
        super(WebController.class, CuisineModel.class);
    }

    @Override
    public CuisineModel toModel(Cuisines entity) {

        CuisineModel cuisinesModel = instantiateModel(entity);

        cuisinesModel.add(linkTo(
                methodOn(WebController.class)
                        .getCuisinesById(entity.getId()))
                .withSelfRel());

        cuisinesModel.setId(entity.getId());
        cuisinesModel.setName(entity.getName());
        return cuisinesModel;
    }

    @Override
    public CollectionModel<CuisineModel> toCollectionModel(Iterable<? extends Cuisines> entities) {

        CollectionModel<CuisineModel> cuisineModels = super.toCollectionModel(entities);

        cuisineModels.add(linkTo(methodOn(WebController.class).getAllCuisines()).withSelfRel());

        return cuisineModels;
    }



}
