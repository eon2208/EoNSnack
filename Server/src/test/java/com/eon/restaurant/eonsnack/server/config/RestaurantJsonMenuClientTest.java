package com.eon.restaurant.eonsnack.server.config;

import com.eon.restaurant.eonsnack.server.client.RestaurantMenuClient;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.Result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantJsonMenuClientTest {

    @Autowired
    RestaurantMenuClient restaurantMenuClient;

    private Result result;
    Logger logger = LoggerFactory.getLogger(RestaurantJsonMenuClientTest.class);

    @BeforeEach
    public void init() {
        result = restaurantMenuClient.getJSONRestaurantWithId(527522).getResult();
        logger.info("setup");
    }

    @Test
    void testAddressForRestaurant() {
        assertEquals(result.getJsonAddress().getCity(),"NJ");
        assertEquals(result.getJsonAddress().getFormatted(),"622 Market Street Elmwood Park, NJ 07407");
        assertEquals(result.getJsonAddress().getState(),"Elmwood Park");
        assertEquals(result.getJsonAddress().getStreet(),"622 Market Street");
    }

    @Test
    void shouldBeEquals() {
        assertEquals(result.getRestaurantId(),527522);
        assertEquals(result.getHours(),"");
        assertEquals(result.getRestaurantName(),"Taco Bell");
        assertEquals(result.getRestaurantPhone(),"(201) 791-2155");
    }

    @Test
    void testGeolocation(){
        assertEquals(result.getJsonGeo().getLon(),-74.103375);
        assertEquals(result.getJsonGeo().getLat(),40.897441);
    }

    @Test
    void testIfMealsNotNull(){
        assertNotNull(result.getJsonMenus());
        assertNotNull(result.getJsonAddress());
    }

}
