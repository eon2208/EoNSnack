package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {

    List<Meal> findAllByRestaurantIdAndTagsId(@RequestParam("/id") long restaurantId, @RequestParam("/id") int tagId);
    List<Meal> findAllByRestaurantId(long id);

}
