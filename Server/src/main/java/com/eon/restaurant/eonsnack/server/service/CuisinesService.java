package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;

import java.util.List;
import java.util.Optional;

public interface CuisinesService {

    Cuisines getByName(String name);

    Boolean existsByName(String name);

    Optional<Cuisines> findById(int id);

    List<Cuisines> findAll();

}
