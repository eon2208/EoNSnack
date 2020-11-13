package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Meal;
import com.eon.restaurant.eonsnack.server.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.cert.PKIXParameters;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService{

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Page<Meal> findAll(Pageable pageable) {
        return mealRepository.findAll(pageable);
    }

    @Override
    public Optional<Meal> findById(long id) {
        return mealRepository.findById(id);
    }

    @Override
    public List<Meal> findAllByRestaurantIdAndTagsId(long restId, int tagId) {
        return mealRepository.findAllByRestaurantIdAndTagsId(restId, tagId);
    }

    @Override
    public List<Meal> findAllByRestaurantId(long id) {
        return mealRepository.findAllByRestaurantId(id);
    }
}
