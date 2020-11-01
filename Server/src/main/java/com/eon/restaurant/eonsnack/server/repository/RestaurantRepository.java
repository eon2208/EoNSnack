package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Address;
import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant getById(long id);

    Optional<Restaurant> findByAddress_Id(int id);

    List<Restaurant> findByRestNameContaining(@RequestParam("name") String name);

}
