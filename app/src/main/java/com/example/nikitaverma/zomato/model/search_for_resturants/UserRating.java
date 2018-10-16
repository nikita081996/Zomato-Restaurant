package com.example.nikitaverma.zomato.model.search_for_resturants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRating implements Serializable {

    @SerializedName("rating_text")
    @Expose
    private String rating_text;
    @SerializedName("rating_color")
    @Expose
    private String rating_color;
    @SerializedName("votes")
    @Expose
    private String votes;
    @SerializedName("aggregate_rating")
    @Expose
    private String aggregateRating;

    public String getRatingText()
    {
        return rating_text;
    }

    public void setRatingText(String rating_text)
    {
        this.rating_text = rating_text;
    }

    public String getRatingColor()
    {
        return rating_color;
    }

    public void setRatingColor(String rating_color)
    {
        this.rating_color = rating_color;
    }

    public String getVotes ()
    {
        return votes;
    }

    public void setVotes (String votes)
    {
        this.votes = votes;
    }

    public String getAggregateRating()
    {
        return aggregateRating;
    }

    public void setAggregateRating(String aggregate_rating)
    {
        this.aggregateRating = aggregate_rating;
    }
}
