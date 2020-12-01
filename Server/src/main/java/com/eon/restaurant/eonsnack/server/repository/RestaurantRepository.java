package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

    Restaurant getById(long id);

    Optional<Restaurant> findByAddress_Id(int id);

    List<Restaurant> findByRestNameContaining(@RequestParam("name") String name);

    @Query("select distinct restaurant \n" +
            "from Restaurant restaurant  \n" +
            "join restaurant.cuisinesList cuisinesList \n" +
            "where cuisinesList.id in :cuisinesId")
    List<Restaurant> findByCuisinesList(List<Integer> cuisinesId);

}
