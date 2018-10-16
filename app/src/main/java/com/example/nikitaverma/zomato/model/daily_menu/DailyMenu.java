package com.example.nikitaverma.zomato.model.daily_menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Base class for Daily Menu api response
 */
public class DailyMenu implements Serializable
{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("daily_menus")
    @Expose
    private DailyMenus[] dailyMenus;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public DailyMenus[] getDailyMenus ()
    {
        return dailyMenus;
    }

    public void setDailyMenus (DailyMenus[] dailyMenus)
    {
        this.dailyMenus = dailyMenus;
    }

}