
package com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantList;

import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.JsonAddress;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.JsonGeo;
import com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals.JsonMenu;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geo",
    "hours",
    "address",
    "restaurant_phone",
    "restaurant_id",
    "menu_tags",
    "price_range",
    "menus",
    "cuisines",
    "restaurant_name",
})
public class Result {

    @JsonProperty("geo")
    private JsonGeo jsonGeo;
    @JsonProperty("hours")
    private String hours;
    @JsonProperty("address")
    private JsonAddress jsonAddress;
    @JsonProperty("restaurant_phone")
    private String restaurantPhone;
    @JsonProperty("restaurant_id")
    private Integer restaurantId;
    @JsonProperty("menu_tags")
    private List<String> menuTags = null;
    @JsonProperty("price_range")
    private String priceRange;
    @JsonProperty("menus")
    private List<JsonMenu> jsonMenus = null;
    @JsonProperty("cuisines")
    private List<String> cuisines = null;
    @JsonProperty("restaurant_name")
    private String restaurantName;
    @JsonProperty("is_in_database")
    private Boolean database;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("geo")
    public JsonGeo getJsonGeo() {
        return jsonGeo;
    }

    @JsonProperty("geo")
    public void setJsonGeo(JsonGeo jsonGeo) {
        this.jsonGeo = jsonGeo;
    }

    @JsonProperty("hours")
    public String getHours() {
        return hours;
    }

    @JsonProperty("hours")
    public void setHours(String hours) {
        this.hours = hours;
    }

    @JsonProperty("address")
    public JsonAddress getJsonAddress() {
        return jsonAddress;
    }

    @JsonProperty("address")
    public void setJsonAddress(JsonAddress jsonAddress) {
        this.jsonAddress = jsonAddress;
    }

    @JsonProperty("restaurant_phone")
    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    @JsonProperty("restaurant_phone")
    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    @JsonProperty("restaurant_id")
    public Integer getRestaurantId() {
        return restaurantId;
    }

    @JsonProperty("restaurant_id")
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @JsonProperty("menu_tags")
    public List<String> getMenuTags() {
        return menuTags;
    }

    @JsonProperty("menu_tags")
    public void setMenuTags(List<String> menuTags) {
        this.menuTags = menuTags;
    }

    @JsonProperty("price_range")
    public String getPriceRange() {
        return priceRange;
    }

    @JsonProperty("price_range")
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    @JsonProperty("menus")
    public List<JsonMenu> getJsonMenus() {
        return jsonMenus;
    }

    @JsonProperty("menus")
    public void setJsonMenus(List<JsonMenu> jsonMenus) {
        this.jsonMenus = jsonMenus;
    }

    @JsonProperty("cuisines")
    public List<String> getCuisines() {
        return cuisines;
    }

    @JsonProperty("cuisines")
    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    @JsonProperty("restaurant_name")
    public String getRestaurantName() {
        return restaurantName;
    }

    @JsonProperty("restaurant_name")
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @JsonProperty("is_in_database")
    public Boolean getDatabase() {
        return database;
    }

    @JsonProperty("is_in_database")
    public void setDatabase(Boolean database) {
        this.database = database;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
