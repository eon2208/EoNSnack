package com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "price",
        "description",
        "position",
        "name",
        "pricing",

})
public class JsonMeal {

    @JsonProperty("price")
    private double price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("position")
    private int position;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pricing")
    private List<JsonPricing> jsonPricing;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pricing")
    public List<JsonPricing> getJsonPricing() {
        return jsonPricing;
    }

    @JsonProperty("pricing")
    public void setJsonPricing(List<JsonPricing> jsonPricing) {
        this.jsonPricing = jsonPricing;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("position")
    public int getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(int position) {
        this.position = position;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
