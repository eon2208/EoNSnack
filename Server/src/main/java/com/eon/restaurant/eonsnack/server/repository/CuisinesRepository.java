package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cuisine", path = "cuisines")
@CrossOrigin("http://localhost:4200")
public interface CuisinesRepository extends PagingAndSortingRepository<Cuisines, Integer> {

    Cuisines getByName(String name);

    Boolean existsByName(String name);

    List<Cuisines> findAllByRestaurant(Restaurant restaurant);
}
