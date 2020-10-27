
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
    "menu_sections",
    "menu_name"
})
public class JsonMenu {

    @JsonProperty("menu_sections")
    private List<MenuSection> menuSections = null;
    @JsonProperty("menu_name")
    private String menuName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("menu_sections")
    public List<MenuSection> getMenuSections() {
        return menuSections;
    }

    @JsonProperty("menu_sections")
    public void setMenuSections(List<MenuSection> menuSections) {
        this.menuSections = menuSections;
    }

    @JsonProperty("menu_name")
    public String getMenuName() {
        return menuName;
    }

    @JsonProperty("menu_name")
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
