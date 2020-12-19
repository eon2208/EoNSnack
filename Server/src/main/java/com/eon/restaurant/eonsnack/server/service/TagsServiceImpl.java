package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.entity.Tags;
import com.eon.restaurant.eonsnack.server.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public boolean existsByName(String tagName) {
        return tagsRepository.existsByName(tagName);
    }

    @Override
    public Tags getByName(String tagName) {
        return tagsRepository.findByName(tagName);
    }

    @Override
    public Page<Tags> findAllTags(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Override
    public Optional<Tags> findById(int id) {
        return tagsRepository.findById(id);
    }

    @Override
    public List<Tags> findAllByIdList(Set<Integer> tagsId) {
        return tagsRepository.findAllByIdList(tagsId);
    }

    @Override
    public void save(Tags tag) {
        tagsRepository.save(tag);
    }

}
