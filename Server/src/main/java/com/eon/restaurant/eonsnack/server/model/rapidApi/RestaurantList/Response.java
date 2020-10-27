
package com.eon.restaurant.eonsnack.server.model.rapidApi.RestaurantList;

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
    "totalResults",
    "data",
    "numResults",
    "page",
    "pages",
    "morePages"
})
public class Response {

    @JsonProperty("totalResults")
    private Integer totalResults;
    @JsonProperty("data")
    private List<Result> data = null;
    @JsonProperty("numResults")
    private Integer numResults;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("pages")
    private Integer pages;
    @JsonProperty("morePages")
    private Boolean morePages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("totalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("data")
    public List<Result> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Result> data) {
        this.data = data;
    }

    @JsonProperty("numResults")
    public Integer getNumResults() {
        return numResults;
    }

    @JsonProperty("numResults")
    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("pages")
    public Integer getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @JsonProperty("morePages")
    public Boolean getMorePages() {
        return morePages;
    }

    @JsonProperty("morePages")
    public void setMorePages(Boolean morePages) {
        this.morePages = morePages;
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
