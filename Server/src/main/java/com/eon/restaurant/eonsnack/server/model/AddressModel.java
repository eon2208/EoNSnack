package com.eon.restaurant.eonsnack.server.model;

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
@Relation(collectionRelation = "addresses", itemRelation = "address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressModel extends RepresentationModel<AddressModel> {

    private int id;
    private String city;
    private String formatted;
    private String street;
    private String state;
    private long postal_code;
}
