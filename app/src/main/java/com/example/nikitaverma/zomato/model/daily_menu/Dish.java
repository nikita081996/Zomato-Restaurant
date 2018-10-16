package com.example.nikitaverma.zomato.model.daily_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dish implements Serializable
{
    @SerializedName("dish_id")
    @Expose
    private String dishId;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("name")
    @Expose
    private String name;

    public String getDishId()
    {
        return dishId;
    }

    public void setDishId(String dishId)
    {
        this.dishId = dishId;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

}

