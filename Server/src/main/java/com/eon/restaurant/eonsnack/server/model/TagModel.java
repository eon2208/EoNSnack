package com.eon.restaurant.eonsnack.server.model;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "tags", itemRelation = "tag")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagModel extends RepresentationModel<TagModel> {

    private int id;
    private String name;
    private Set<Restaurant> restaurants;
    private Set<Preferences> preferences;
}
