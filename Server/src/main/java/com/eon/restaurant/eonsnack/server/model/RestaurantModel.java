package com.eon.restaurant.eonsnack.server.model;

import com.eon.restaurant.eonsnack.server.entity.*;
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
@Relation(collectionRelation = "restaurants", itemRelation = "restaurant")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    private Long id;
    private String restName;
    private String hours;
    private String restNumber;
    private List<Cuisines> cuisines;
    private Geolocation geolocation;
    private Address address;

    //private List<TagModel> tagsList;
    private List<MealModel> meals;
    private List<CuisineModel> cuisinesList;
}
