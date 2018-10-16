package com.example.nikitaverma.zomato.api_call;

import com.example.nikitaverma.zomato.model.daily_menu.DailyMenu;
import com.example.nikitaverma.zomato.model.get_list_of_categories.GetCategoryListForNavigationMenu;
import com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city.ZomatoCollection;
import com.example.nikitaverma.zomato.model.search_for_locations.GetEntityId;
import com.example.nikitaverma.zomato.model.search_for_resturants.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZomatoApi {
    /**
     * Search for location api
     *
     * @param query is location name
     * @return entityId and entityType of particular location
     */
    @GET("api/v2.1/locations")
    Call<GetEntityId> getEntityId(@Query("query") String query);

    /**
     * Search for Restaurants api call
     *
     * @param entityId   can get from location api of Zomato api call
     * @param entityType can get from location api of Zomato api call
     * @param query  is type of meals
     * @return list of restaurants in particular locaation
     */
    @GET("api/v2.1/search")
    Call<Example> getRestaurants(@Query("entity_id") String entityId,
                                 @Query("entity_type") String entityType, @Query("q") String query);

    /**
     * List of Categories api call
     *
     * @return list of categories of Zomato api
     */

    @GET("api/v2.1/categories")
    Call<GetCategoryListForNavigationMenu> getListOfCategoriesForNavigationMenu();

    /**
     * Collection api call for paricular location
     *
     * @param cityId is location id
     * @return list of collection
     */
    @GET("api/v2.1/collections")
    Call<ZomatoCollection> getListOfZomatoCollection(@Query("city_id") String cityId);

    /**
     * Daily Menu api call
     *
     * @param resId is retaurant id
     * @return list of daily menu
     */
    @GET("api/v2.1/dailymenu")
    Call<DailyMenu> getListOfDailyMenu(@Query("res_id") String resId);
}