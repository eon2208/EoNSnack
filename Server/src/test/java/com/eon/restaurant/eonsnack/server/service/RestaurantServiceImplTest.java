package com.eon.restaurant.eonsnack.server.service;

import com.eon.restaurant.eonsnack.server.entity.Cuisines;
import com.eon.restaurant.eonsnack.server.entity.Restaurant;
import com.eon.restaurant.eonsnack.server.repository.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    Logger logger = LoggerFactory.getLogger(RestaurantServiceImplTest.class);

    @Test
    @DisplayName("filter restaurants by cuisines id list")
    void filterAllRestaurantsByCuisineId() {
        List<Integer> cuisinesId = new ArrayList<>();
        cuisinesId.add(1);
        cuisinesId.add(3);
        cuisinesId.add(6);
        cuisinesId.add(5);

        List<Cuisines> cuisinesList1 = new ArrayList<>();
        cuisinesList1.add(new Cuisines(1, "test1"));
        cuisinesList1.add(new Cuisines(2, "test2"));
        cuisinesList1.add(new Cuisines(6, "test6"));

        List<Cuisines> cuisinesList2 = new ArrayList<>();
        cuisinesList2.add(new Cuisines(3, "test3"));
        cuisinesList2.add(new Cuisines(4, "test4"));
        cuisinesList2.add(new Cuisines(6, "test6"));

        List<Cuisines> cuisinesList3 = new ArrayList<>();
        cuisinesList3.add(new Cuisines(1, "test1"));
        cuisinesList3.add(new Cuisines(2, "test2"));
        cuisinesList3.add(new Cuisines(3, "test3"));
        cuisinesList3.add(new Cuisines(4, "test4"));

        List<Cuisines> cuisinesList4 = new ArrayList<>();
        cuisinesList4.add(new Cuisines(2, "test2"));

        Restaurant restaurant1 = new Restaurant(1, "rest1", cuisinesList1);
        Restaurant restaurant2 = new Restaurant(2, "rest2", cuisinesList2);
        Restaurant restaurant3 = new Restaurant(3, "rest3", cuisinesList3);
        Restaurant restaurant4 = new Restaurant(4, "rest4", cuisinesList4);

        List<Restaurant> restaurantList = Arrays.asList(restaurant1,restaurant2,restaurant3,restaurant4);
        Page<Restaurant> restaurantPage = new PageImpl<>(restaurantList);

        doReturn(restaurantPage).when(restaurantRepository).findAll(Pageable.unpaged());

        Page<Restaurant> filteredList = restaurantService.getFilteredListOfRestaurantsByCuisinesId(cuisinesId);

        Assertions.assertEquals(filteredList.getTotalElements(), 3);
    }
}
