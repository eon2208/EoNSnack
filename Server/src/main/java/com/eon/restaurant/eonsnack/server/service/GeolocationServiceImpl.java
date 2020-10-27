package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Geolocation;
import com.eon.restaurant.eonsnack.server.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    @Autowired
    GeolocationRepository geolocationRepository;

    @Override
    public Optional<Geolocation> findById(int id) {
        return geolocationRepository.findById(id);
    }
}
