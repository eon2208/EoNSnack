package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import com.eon.restaurant.eonsnack.server.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface GeolocationService {

    Optional<Geolocation> findById(int id);
}
