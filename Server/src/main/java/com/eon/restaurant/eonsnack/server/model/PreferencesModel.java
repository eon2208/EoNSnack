package com.eon.restaurant.eonsnack.server.model;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.entity.User;
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
@Relation(collectionRelation = "preferences", itemRelation = "preferences")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreferencesModel extends RepresentationModel<PreferencesModel>  {

        private long id;
        private User user;

        Set<Cuisines> cuisinesSet;
        Set<Tags> tagsSet;
        Set<Restaurant> restaurantSet;
}
