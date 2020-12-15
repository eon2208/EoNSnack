package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagsService {
    
    boolean existsByName(String tagName);

    Tags getByName(String tagName);

    Page<Tags> findAllTags(Pageable pageable);

    Optional<Tags> findById(int id);

    List<Tags> findAllByIdList(Set<Integer> tagsId);

    void save(Tags tag);
}
