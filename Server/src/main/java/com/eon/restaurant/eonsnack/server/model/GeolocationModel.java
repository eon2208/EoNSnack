package com.eon.restaurant.eonsnack.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "cuisines", itemRelation = "cuisine")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeolocationModel extends RepresentationModel<GeolocationModel> {

    private int id;
    private double lon;
    private double lat;
}
