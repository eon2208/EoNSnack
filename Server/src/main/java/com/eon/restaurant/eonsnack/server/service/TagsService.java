package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;

import java.util.List;
import java.util.Optional;

public interface TagsService {
    
    boolean existsByName(String tagName);

    Tags getByName(String tagName);

    List<Tags> findAll();

    Optional<Tags> findById(int id);

    void save(Tags tag);
}
