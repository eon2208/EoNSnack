package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findAllByRestaurantId(long id);

}
