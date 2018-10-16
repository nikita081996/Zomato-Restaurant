package com.example.nikitaverma.zomato.model.daily_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Daily_menu implements Serializable
{
    @SerializedName("daily_menu_id")
    @Expose
    private String dailyMenuId;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dishes")
    @Expose
    private Dishes[] dishes;
    @SerializedName("start_date")
    @Expose
    private String startDate;

    public String getDailyMenuId()
    {
        return dailyMenuId;
    }

    public void setDailyMenuId(String daily_menu_id)
    {
        this.dailyMenuId = daily_menu_id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Dishes[] getDishes ()
    {
        return dishes;
    }

    public void setDishes (Dishes[] dishes)
    {
        this.dishes = dishes;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String start_date)
    {
        this.startDate = start_date;
    }

}