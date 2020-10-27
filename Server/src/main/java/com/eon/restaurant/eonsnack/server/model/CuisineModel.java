package com.eon.restaurant.eonsnack.server.model;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "cuisines", itemRelation = "cuisine")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuisineModel extends RepresentationModel<CuisineModel> {

    private int id;
    private String name;

    List<RestaurantModel> restaurants;
}
