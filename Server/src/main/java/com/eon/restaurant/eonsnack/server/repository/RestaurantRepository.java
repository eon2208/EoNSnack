package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("http://localhost:4200")
@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

    Restaurant getById(long id);

    Optional<Restaurant> findByAddress_Id(int id);

    List<Restaurant> findByRestNameContaining(@RequestParam("name") String name);

    @Query("select distinct restaurant \n" +
            "from Restaurant restaurant  \n" +
            "join restaurant.cuisinesList cuisinesList \n" +
            "where cuisinesList.id in :cuisinesId \n")
    List<Restaurant> findByCuisinesId(List<Integer> cuisinesId);

    @Query("select distinct restaurant \n" +
            "from Restaurant restaurant  \n" +
            "join restaurant.tagsList tagsList \n" +
            "where tagsList.id in :tagsId \n")
    List<Restaurant> findByTagsId(List<Integer> tagsId);

}
