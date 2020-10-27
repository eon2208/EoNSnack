package com.eon.restaurant.eonsnack.server.repository;

import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {


}
