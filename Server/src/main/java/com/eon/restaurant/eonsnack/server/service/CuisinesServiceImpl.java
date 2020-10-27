package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.repository.CuisinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisinesServiceImpl implements CuisinesService {

    @Autowired
    private CuisinesRepository cuisinesRepository;

    @Override
    public Cuisines getByName(String name) {

        return cuisinesRepository.getByName(name);
    }

    @Override
    public Boolean existsByName(String name) {

        return cuisinesRepository.existsByName(name);
    }

    @Override
    public Optional<Cuisines> findById(int id) {
        return cuisinesRepository.findById(id);
    }

    @Override
    public List<Cuisines> findAll() {
        return cuisinesRepository.findAll();
    }

}
