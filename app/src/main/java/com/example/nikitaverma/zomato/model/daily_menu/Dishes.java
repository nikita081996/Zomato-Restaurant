package com.example.nikitaverma.zomato.model.daily_menu;

import java.io.Serializable;

public class Dishes implements Serializable
{
    private Dish dish;

    public Dish getDish ()
    {
        return dish;
    }

    public void setDish (Dish dish)
    {
        this.dish = dish;
    }

}
