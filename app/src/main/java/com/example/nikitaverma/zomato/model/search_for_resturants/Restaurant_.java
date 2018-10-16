package com.example.nikitaverma.zomato.model.search_for_resturants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Restaurant_ implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("has_online_delivery")
    @Expose
    private Integer hasOnlineDelivery;
    @SerializedName("is_delivering_now")
    @Expose
    private Integer isDeliveringNow;
    @SerializedName("average_cost_for_two")
    @Expose
    private Integer averageCostForTwo;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("R")
    @Expose
    private R R;
    @SerializedName("zomato_events")
    @Expose
    private List<ZomatoEvent> zomatoEvents = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public Integer getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public List<ZomatoEvent> getZomatoEvents() {
        return zomatoEvents;
    }

    public void setZomatoEvents(List<ZomatoEvent> zomatoEvents) {
        this.zomatoEvents = zomatoEvents;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public Integer getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(Integer hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public Integer getDeliveringNow() {
        return isDeliveringNow;
    }

    public void setDeliveringNow(Integer deliveringNow) {
        isDeliveringNow = deliveringNow;
    }

    public Integer getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(Integer isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public com.example.nikitaverma.zomato.model.search_for_resturants.R getR() {
        return R;
    }

    public void setR(com.example.nikitaverma.zomato.model.search_for_resturants.R r) {
        R = r;
    }
}