package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CuisinesService {

    Cuisines getByName(String name);

    Boolean existsByName(String name);

    Optional<Cuisines> findById(int id);

    Page<Cuisines> findAll(Pageable pageable);

    List<Cuisines> getCuisinesByIdList(List<Integer> cuisinesId);
}
