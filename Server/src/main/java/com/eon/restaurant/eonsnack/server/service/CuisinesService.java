package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CuisinesService {

    Cuisines getByName(String name);

    Boolean existsByName(String name);

    Optional<Cuisines> findById(int id);

    List<Cuisines> findAllByIdList(Set<Integer> cuisinesId);

    Page<Cuisines> findAll(Pageable pageable);

    List<Cuisines> findAllByRestaurantId(long id);
}
