package com.eon.restaurant.eonsnack.server.model.JSONParse;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "tags"
})
public class UserResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("tags")
    private List<TagResponseModel> responseModels;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("tags")
    public List<TagResponseModel> getResponseModels() {
        return responseModels;
    }

    @JsonProperty("tags")
    public void setResponseModels(List<TagResponseModel> responseModels) {
        this.responseModels = responseModels;
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
