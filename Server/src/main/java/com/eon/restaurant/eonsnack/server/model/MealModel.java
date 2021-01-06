package com.eon.restaurant.eonsnack.server.model;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "meals", itemRelation = "meal")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MealModel extends RepresentationModel<MealModel> {

    private long id;
    private String name;
    private double price;
    private String description;
    private Tags tag;

    private Restaurant restaurant;
}
