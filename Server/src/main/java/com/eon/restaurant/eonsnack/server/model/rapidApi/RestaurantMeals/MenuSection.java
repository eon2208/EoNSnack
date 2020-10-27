package com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantMeals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "section_name",
        "description",
        "menu_items",
        "position",
})
public class MenuSection {

    @JsonProperty("section_name")
    private String sectionName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("menu_items")
    private List<JsonMeal> menuItems = null;
    @JsonProperty("position")
    private Integer position;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    @JsonProperty("section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("menu_items")
    public List<JsonMeal> getMenuItems() {
        return menuItems;
    }

    @JsonProperty("menu_items")
    public void setMenuItems(List<JsonMeal> menuItems) {
        this.menuItems = menuItems;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
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


