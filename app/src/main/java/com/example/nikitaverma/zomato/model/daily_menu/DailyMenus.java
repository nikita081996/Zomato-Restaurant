package com.example.nikitaverma.zomato.model.daily_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DailyMenus implements Serializable {
    @SerializedName("daily_menu")
    @Expose
    private Daily_menu dailyMenu;

    public Daily_menu getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(Daily_menu dailyMenu) {
        this.dailyMenu = dailyMenu;
    }

}