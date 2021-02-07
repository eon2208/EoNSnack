package com.eon.restaurant.eonsnack.server.client;

import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantList.RestaurantsList;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantList.Result;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.RestaurantJsonItem;
import com.eon.restaurant.eonsnack.server.service.JsonToEntity;
import com.eon.restaurant.eonsnack.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class RestaurantMenuClient {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private JsonToEntity jsonToEntity;

    @Value("${rapidapi.restaurant.url}")
    String restaurantUrl;

    @Value("${rapidapi.restaurantList.url}")
    String restaurantsListUrl;

    @Value("${rapidapi.key.name}")
    String apiKeyName;

    @Value("${rapidapi.key.value}")
    String apiKeyValue;

    @Value("${rapidapi.host.name}")
    String hostName;

    @Value("${rapidapi.host.value}")
    String hostValue;

    private Logger logger = Logger.getLogger(getClass().getName());

    RestTemplate restTemplate;

    public RestaurantMenuClient() {
    }

    public RestaurantMenuClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public RestaurantJsonItem getJSONRestaurantWithId(long id) {
        RestaurantJsonItem restaurantJsonItem = null;
        try {
            ResponseEntity<RestaurantJsonItem> totalEntity = restTemplate.exchange(
                    factorRestaurantUrl(id), HttpMethod.GET, createRequest(), RestaurantJsonItem.class);
            restaurantJsonItem = totalEntity.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return restaurantJsonItem;
    }

    public RestaurantsList getJsonRestaurantsList(int page){
        RestaurantsList restaurantsList = null;

        try {
            ResponseEntity<RestaurantsList> totalEntity = restTemplate.exchange(
                    factorRestaurantListUrl(page), HttpMethod.GET, createRequest(), RestaurantsList.class);
            restaurantsList = totalEntity.getBody();
            assert restaurantsList != null;
            existsInDatabase(restaurantsList.getResult().getData());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return restaurantsList;
    }

    public void saveRestaurantsPage(int page) {
        RestaurantsList restaurantsList = getJsonRestaurantsList(page);

        List<Result> restaurantObject = restaurantsList.getResult().getData();
        for(Result result : restaurantObject){
            if(!restaurantIsInDatabase(result)){
                jsonToEntity.saveRestaurantFromJSON(result.getRestaurantId());
            }
            else
                logger.info("restaurant already exists");
        }
    }

    private void existsInDatabase(List<Result> data) {
        for(Result result : data){
            result.setDatabase(restaurantIsInDatabase(result));
        }
    }

    private boolean restaurantIsInDatabase(Result result) {

        return restaurantService.existsById((long)result.getRestaurantId());
    }

    private HttpEntity<String> createRequest() {

        return new HttpEntity<>(setUpHeader());
    }

    private HttpHeaders setUpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKeyName, apiKeyValue);
        headers.set(hostName, hostValue);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        logger.info("header set");
        return headers;
    }

    private URI factorRestaurantUrl(long id) throws URISyntaxException {

        URI uri = new URI(restaurantUrl + id);
        logger.info("URI: " + uri);

        return uri;
    }

    private URI factorRestaurantListUrl(int page) throws URISyntaxException {

        URI uri = new URI(restaurantsListUrl + page);
        logger.info("URI:" + uri);

        return uri;
    }
}
