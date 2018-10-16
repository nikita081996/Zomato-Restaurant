package com.example.nikitaverma.zomato.model.search_for_locations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Base class for search for location api response
 */
public class GetEntityId implements Serializable{

    @SerializedName("has_total")
    @Expose
    private String hasTotal;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("has_more")
    @Expose
    private String hasMore;

    @SerializedName("location_suggestions")
    @Expose
    private Location_suggestions[] locationSuggestions;

    public String getHasTotal()
    {
        return hasTotal;
    }

    public void setHasTotal(String has_total)
    {
        this.hasTotal = has_total;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getHasMore()
    {
        return hasMore;
    }

    public void setHasMore(String has_more)
    {
        this.hasMore = has_more;
    }

    public Location_suggestions[] getLocationSuggestions()
    {
        return locationSuggestions;
    }

    public void setLocationSuggestions(Location_suggestions[] location_suggestions)
    {
        this.locationSuggestions = location_suggestions;
    }

    public class Location_suggestions
    {
        @SerializedName("entity_id")
        @Expose
        private String entityId;
        @SerializedName("entity_type")
        @Expose
        private String entityType;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("city_id")
        @Expose
        private String cityId;
        @SerializedName("city_name")
        @Expose
        private String cityName;
        @SerializedName("country_id")
        @Expose
        private String countryId;
        @SerializedName("country_name")
        @Expose
        private String countryName;

        public String getEntityId() {
            return entityId;
        }

        public void setEntityId(String entity_id) {
            this.entityId = entity_id;
        }

        public String getEntityType() {
            return entityType;
        }

        public void setEntityType(String entity_type) {
            this.entityType = entity_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String city_id) {
            this.cityId = city_id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String city_name) {
            this.cityName = city_name;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String country_id) {
            this.countryId = country_id;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String country_name) {
            this.countryName = country_name;
        }
    }

}