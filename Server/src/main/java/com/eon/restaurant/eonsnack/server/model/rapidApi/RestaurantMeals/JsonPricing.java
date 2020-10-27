package com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "priceString",
        "price",
        "currency"
})
public class JsonPricing {

    @JsonProperty("priceString")
    private String priceString;

    @JsonProperty("price")
    private double price;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("priceString")
    public String getPriceString() {
        return priceString;
    }

    @JsonProperty("priceString")
    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
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
