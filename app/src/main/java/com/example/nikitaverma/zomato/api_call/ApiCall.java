package com.example.nikitaverma.zomato.api_call;

import android.view.View;

import com.example.nikitaverma.zomato.model.daily_menu.DailyMenu;
import com.example.nikitaverma.zomato.model.get_list_of_categories.GetCategoryListForNavigationMenu;
import com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city.ZomatoCollection;
import com.example.nikitaverma.zomato.model.search_for_locations.GetEntityId;
import com.example.nikitaverma.zomato.model.search_for_resturants.Example;
import com.example.nikitaverma.zomato.view.common.MainActivity;
import com.example.nikitaverma.zomato.view.restaurant_view.RestaurantDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nikitaverma.zomato.utility.Constants.ZOMATO_SERVICE_STATUS;
import static com.example.nikitaverma.zomato.view.common.MainActivity.categoryList;
import static com.example.nikitaverma.zomato.view.common.MainActivity.getEntityId;
import static com.example.nikitaverma.zomato.view.common.MainActivity.nameOfClickedNavigationItem;
import static com.example.nikitaverma.zomato.view.common.MainActivity.restaurants;
import static com.example.nikitaverma.zomato.view.common.MainActivity.zomatoCollection;
import static com.example.nikitaverma.zomato.view.daily_menu.DailyMenuFragment.dailyMenu;

/**
 * This class contain all Zomato api call
 */
public class ApiCall {

    /**
     * call for dailyMenu Api of Zomato Api
     *
     * @param resId   is restaurantId
     * @param listner is response for server
     */
    public void getDailyMenuList(final String resId, final ResponseListner listner) {
        Call<DailyMenu> call = MainActivity.mApiInterface.getListOfDailyMenu(resId);

        call.enqueue(new Callback<DailyMenu>() {
            @Override
            public void onResponse(Call<DailyMenu> call, Response<DailyMenu> response) {
                if (response.code() == 200) {
                    dailyMenu = response.body();
                    listner.responseFromServer(dailyMenu);

                } else {
                    RestaurantDetailsActivity.mMenuAvailabilityTv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<DailyMenu> call, Throwable t) {
                listner.responseFromServer(t.getMessage());
            }
        });
    }

    /**
     * call for search for location api of Zomato api
     *
     * @param listner is response for server
     */
    public void setGetEntityIdAndEntityType(final ResponseListner listner) {
        Call<GetEntityId> call = MainActivity.mApiInterface.getEntityId(MainActivity.mCurrentLocationTv.getText().toString());

        call.enqueue(new Callback<GetEntityId>() {
            @Override
            public void onResponse(Call<GetEntityId> call, Response<GetEntityId> response) {
                if (response.code() == 200) {
                    getEntityId = response.body();
                    listner.responseFromServer(getEntityId);

                } else {
                    listner.responseFromServer(ZOMATO_SERVICE_STATUS);
                }
            }

            @Override
            public void onFailure(Call<GetEntityId> call, Throwable t) {
                listner.responseFromServer(t.getMessage());
            }
        });
    }

    /**
     * call for search restaurants based on entity (location) id of Zomato api
     *
     * @param entityId   is locationId
     * @param entityType is locationType
     */
    public void listOfrestaurants(String entityId, String entityType, final ResponseListner listner) {
        Call<Example> call;
        if (nameOfClickedNavigationItem != null) {
            call = MainActivity.mApiInterface.getRestaurants(entityId, entityType, nameOfClickedNavigationItem + " " + MainActivity.mEnterTypeOfMealsEt.getText().toString());
        } else {
            call = MainActivity.mApiInterface.getRestaurants(entityId, entityType, MainActivity.mEnterTypeOfMealsEt.getText().toString());
        }

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    restaurants = response.body();
                    listner.responseFromServer(restaurants);

                } else {
                    listner.responseFromServer(ZOMATO_SERVICE_STATUS);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                listner.responseFromServer(t.getMessage());
            }
        });
    }

    /**
     * call for list of categories api of Zomato api
     *
     * @param listner is response for server
     */
    public void listOfCategoriesForNavigationMenu(final ResponseListner listner) {
        Call<GetCategoryListForNavigationMenu> call = MainActivity.mApiInterface.getListOfCategoriesForNavigationMenu();

        call.enqueue(new Callback<GetCategoryListForNavigationMenu>() {
            @Override
            public void onResponse(Call<GetCategoryListForNavigationMenu> call, Response<GetCategoryListForNavigationMenu> response) {
                if (response.code() == 200) {
                    categoryList = response.body();
                    listner.responseFromServer(categoryList);

                } else {
                    listner.responseFromServer(ZOMATO_SERVICE_STATUS);
                }
            }

            @Override
            public void onFailure(Call<GetCategoryListForNavigationMenu> call, Throwable t) {
                listner.responseFromServer(t.getMessage());
            }
        });
    }

    /**
     * call for Zomato collection api on particular location
     *
     * @param cityId
     */
    public void listOfZomatoCollection(String cityId, final ResponseListner listner) {
        Call<ZomatoCollection> call = MainActivity.mApiInterface.getListOfZomatoCollection(cityId);

        call.enqueue(new Callback<ZomatoCollection>() {
            @Override
            public void onResponse(Call<ZomatoCollection> call, Response<ZomatoCollection> response) {
                if (response.code() == 200) {
                    zomatoCollection = response.body();
                    listner.responseFromServer(zomatoCollection);

                } else {
                    listner.responseFromServer("");
                }
            }

            @Override
            public void onFailure(Call<ZomatoCollection> call, Throwable t) {
                listner.responseFromServer(t.getMessage());
            }
        });

    }
}