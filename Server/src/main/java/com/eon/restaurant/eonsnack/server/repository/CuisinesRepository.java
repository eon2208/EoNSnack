package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "cuisine", path = "cuisines")
@CrossOrigin("http://localhost:4200")
@Repository
public interface CuisinesRepository extends PagingAndSortingRepository<Cuisines, Integer> {

    Cuisines getByName(String name);

    Boolean existsByName(String name);

    List<Cuisines> findByRestaurant(Restaurant restaurant);

    @Query("select cuisines from Cuisines cuisines where cuisines.id in (:cuisinesId)")
    List<Cuisines> findAllByIdList(Set<Integer> cuisinesId);
}
