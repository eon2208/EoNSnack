package com.eon.restaurant.eonsnack.server.model.assembler;

import com.eon.restaurant.eonsnack.server.controller.PreferencesController;
import com.eon.restaurant.eonsnack.server.controller.TagsController;
import com.eon.restaurant.eonsnack.server.entity.Preferences;
import com.eon.restaurant.eonsnack.server.model.PreferencesModel;
import com.eon.restaurant.eonsnack.server.model.TagModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PreferencesModelAssembler extends RepresentationModelAssemblerSupport<Preferences, PreferencesModel> {

    public PreferencesModelAssembler() {
        super(PreferencesController.class, PreferencesModel.class);
    }

    @Override
    public PreferencesModel toModel(Preferences entity) {

        PreferencesModel preferencesModel = buildPreferencesModel(entity);

        return preferencesModel;
    }

    public PreferencesModel buildPreferencesModel(Preferences entity){

        return PreferencesModel.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .cuisinesSet(entity.getCuisines())
                .restaurantSet(entity.getRestaurants())
                .tagsSet(entity.getTags())
                .build();
    }

    @Override
    public CollectionModel<PreferencesModel> toCollectionModel(Iterable<? extends Preferences> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }
}
