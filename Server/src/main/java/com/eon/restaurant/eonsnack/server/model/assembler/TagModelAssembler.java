
package com.eon.restaurant.eonsnack.server.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.eon.restaurant.eonsnack.server.controller.TagsController;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.model.TagModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TagModelAssembler extends RepresentationModelAssemblerSupport<Tags, TagModel> {

    public TagModelAssembler() {
        super(TagsController.class, TagModel.class);
    }

    @Override
    public TagModel toModel(Tags entity) {

        TagModel tagModel = buildTagModel(entity);

        tagModel.add(linkTo(
                methodOn(TagsController.class)
                        .getTagsById(entity.getId()))
                .withSelfRel());

        return tagModel;
    }

    public TagModel buildTagModel(Tags entity){

        return TagModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .restaurants(entity.getRestaurants())
                .build();
    }

    @Override
    public CollectionModel<TagModel> toCollectionModel(Iterable<? extends Tags> entities) {

        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel:: of));
    }
}

