
package com.eon.restaurant.eonsnack.server.model.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.eon.restaurant.eonsnack.server.controller.WebController;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.model.TagModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TagModelAssembler extends RepresentationModelAssemblerSupport<Tags, TagModel> {

    public TagModelAssembler() {
        super(WebController.class, TagModel.class);
    }

    @Override
    public TagModel toModel(Tags entity) {

        TagModel tagModel = instantiateModel(entity);

        tagModel.add(linkTo(
                methodOn(WebController.class)
                        .getTagsById(entity.getId()))
                .withSelfRel());

        tagModel.setId(entity.getId());
        tagModel.setName(entity.getName());

        return tagModel;
    }

    @Override
    public CollectionModel<TagModel> toCollectionModel(Iterable<? extends Tags> entities) {

        CollectionModel<TagModel> tagModels = super.toCollectionModel(entities);

        tagModels.add(linkTo(methodOn(WebController.class).getAllTags()).withSelfRel());

        return tagModels;
    }

}

