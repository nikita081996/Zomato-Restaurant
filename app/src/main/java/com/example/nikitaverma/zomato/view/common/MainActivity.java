package com.example.nikitaverma.zomato.view.common;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.adapter.CategoryListForNavigationMenuAdapter;
import com.example.nikitaverma.zomato.adapter.RestaurantAdapter;
import com.example.nikitaverma.zomato.adapter.ZomatoCollectionAdapter;
import com.example.nikitaverma.zomato.api_call.ApiCall;
import com.example.nikitaverma.zomato.api_call.ResponseListner;
import com.example.nikitaverma.zomato.api_call.ZomatoApi;
import com.example.nikitaverma.zomato.api_call.ZomatoApiClient;
import com.example.nikitaverma.zomato.model.get_list_of_categories.GetCategoryListForNavigationMenu;
import com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city.Collections;
import com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city.ZomatoCollection;
import com.example.nikitaverma.zomato.model.search_for_locations.GetEntityId;
import com.example.nikitaverma.zomato.model.search_for_resturants.Example;
import com.example.nikitaverma.zomato.model.search_for_resturants.Restaurant;
import com.example.nikitaverma.zomato.utility.Constants;
import com.example.nikitaverma.zomato.utility.MyFragment;
import com.example.nikitaverma.zomato.utility.StringFormatter;
import com.example.nikitaverma.zomato.utility.ToastUtility;
import com.example.nikitaverma.zomato.view.category_view.NavigationFragment;
import com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment.mAdapter;
import static com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment.mAdapterForZomatoCollection;
import static com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment.mRecyclerView;
import static com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment.mRecyclerViewForZomatoCollection;
import static com.example.nikitaverma.zomato.view.restaurant_view.RestaurantFragment.mTitle;

public class MainActivity extends AppCompatActivity
        implements  View.OnClickListener, Serializable, Constants, ResponseListner {

    public static int mFlag = 0;
    public static EditText mEnterTypeOfMealsEt;
    public static ZomatoApi mApiInterface;
    public static ProgressBar mFetchProgress;
    public static TextView mCurrentLocationTv;
    public static TextView mRecommendationTv;
    public static GetEntityId getEntityId;
    public static String nameOfClickedNavigationItem;
    public static Example restaurants;
    public static GetCategoryListForNavigationMenu categoryList;
    public static ZomatoCollection zomatoCollection;
    private static RestaurantFragment mRestaurantFragment;
    private LocationManager mLocationManager;
    private TextView mChangeLocationTv;
    private RecyclerView mNavRecyclerView;
    private CategoryListForNavigationMenuAdapter mNavAdapter;
    private Toolbar mToolbar;
    private ImageButton mFindCurrentLocationBtn;
    private TextView mSearchWithMealsBtn;
    private ProgressBar mNavProgressBar;
    private ApiCall mApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        floatingActionButtonAndDrawerLayout();
        initialize();
        updateLocation();
    }

    /**
     * initialize all variables
     */
    private void initialize() {
        mEnterTypeOfMealsEt = findViewById(R.id.location);
        mRecommendationTv = findViewById(R.id.recommendation_text);
        mChangeLocationTv = findViewById(R.id.change_location);
        mCurrentLocationTv = findViewById(R.id.current_location);
        mFetchProgress = findViewById(R.id.fetch_progress);
        mFindCurrentLocationBtn = findViewById(R.id.find_current_location);
        mSearchWithMealsBtn = findViewById(R.id.search_with_meals);

        RecyclerView.LayoutManager mNavLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mNavRecyclerView.setLayoutManager(mNavLayoutManager);
        mNavRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mChangeLocationTv.setOnClickListener(this);
        mFindCurrentLocationBtn.setOnClickListener(this);
        mSearchWithMealsBtn.setOnClickListener(this);

    }

    private void floatingActionButtonAndDrawerLayout() {
        mApiCall = new ApiCall();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mApiInterface = ZomatoApiClient.getInstance().create(ZomatoApi.class);

        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerLayout = navigationView.getHeaderView(0);
        mNavRecyclerView = headerLayout.findViewById(R.id.nav_recycler_view);
        mNavProgressBar = headerLayout.findViewById(R.id.nav_progress_bar);
        mApiCall.listOfCategoriesForNavigationMenu(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_location:
                createDialogBox();
                break;

            case R.id.find_current_location:
                mFlag = 0;
                updateLocation();
                break;

            case R.id.search_with_meals:
                if (haveNetworkConnection())
                {
                    fetchMainActivityData();
                }
                else
                {
                    ToastUtility.showToast(getApplicationContext(), INTERNET_CONNECTION_STATUS);
                }
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                } catch (Exception e) {

                }
                break;
        }
    }


    /**
     * Fetching mainactivity data
     */
    public void fetchMainActivityData() {
        if (mRestaurantFragment == null) {
            mRestaurantFragment = new RestaurantFragment();
            mApiCall.setGetEntityIdAndEntityType(this);

            MyFragment myFragment = new MyFragment();
            FragmentManager fm = getSupportFragmentManager();
            myFragment.getFragment(fm, mRestaurantFragment, R.id.fragment_data);
        } else {
            mApiCall.setGetEntityIdAndEntityType(this);
        }
        nameOfClickedNavigationItem = null;
        mFetchProgress.setVisibility(View.VISIBLE);
    }


    /**
     * api call based on navigation item clicked
     *
     * @param name is name of the clicked navigation item
     */

    public void listOfNavigationClickedData(String name) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        mFetchProgress.setVisibility(View.VISIBLE);
        nameOfClickedNavigationItem = name;
        mApiCall.setGetEntityIdAndEntityType(this);
        NavigationFragment fragment = new NavigationFragment();
        FragmentManager fm = getSupportFragmentManager();
        MyFragment myFragment = new MyFragment();
        myFragment.getFragment(fm, fragment, R.id.fragment_data);
        mRestaurantFragment = null;
    }


    /**
     * Turn on location dialog box
     *
     * @param context is application context
     */
    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, ALL_LOCATION_SETTINGS_SATISFIED);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, ALL_LOCATION_SETTINGS_NOT_SATISFIED);

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, 0);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, PENDING_INTENT);
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, LOCATION_SETTING_NOT_SUPPORTED);
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // This log is never called
        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case 0:
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        // All required changes were successfully made
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        displayLocationSettingsRequest(this);
                        // The user was asked to change settings, but chose not to
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            case 1:
                if (haveNetworkConnection()) {
                    fetchMainActivityData();
                } else {
                    ToastUtility.showToast(getApplicationContext(), INTERNET_CONNECTION_STATUS);
                }
                break;

        }
    }

    /**
     * permission to access location and find current location
     */
    private void updateLocation() {

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            displayLocationSettingsRequest(this);
            LocationListener mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (mFlag == 0) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        //get the location name from latitude and longitude
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        try {
                            List<Address> addresses =
                                    geocoder.getFromLocation(latitude, longitude, 1);
                            if(addresses!=null && addresses.size()!=0) {
                                String result = addresses.get(0).getSubLocality() + ", ";
                                // result += addresses.get(0).getSubLocality() + ", ";
                                result += addresses.get(0).getLocality() + ", ";
                                result += addresses.get(0).getAdminArea() + ", ";
                                result += addresses.get(0).getCountryName();
                                //    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                mCurrentLocationTv.setText(result);
                            }
                            if (!getSupportFragmentManager().isDestroyed()) {
                                if (haveNetworkConnection()) {
                                    fetchMainActivityData();
                                    mFlag = 1;
                                } else {
                                    ToastUtility.showToast(getApplicationContext(), INTERNET_CONNECTION_STATUS);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    mLocationManager.isProviderEnabled(provider);
                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
            }
           // mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    updateLocation();

                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, PERMISSION_DENIED, Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
                break;
            default:
                //   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * check for internet connection
     *
     * @return true if network is available else return false
     */
    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase(WIFI))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase(MOBILE))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFlag = 0;
    }

    /**
     * Custom Alert dialog box
     */
    public void createDialogBox() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle(ALERT_DIALOG_TITLE);
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_location);

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(ALERT_DIALOG_POSITIVE_BUTTON_TEXT,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.mFlag = 1;
                        MainActivity.mCurrentLocationTv.setText(input.getText().toString());
                        if (haveNetworkConnection()) {
                            fetchMainActivityData();
                        } else {
                            ToastUtility.showToast(getApplicationContext(), INTERNET_CONNECTION_STATUS);
                        }
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton(ALERT_DIALOG_NEGATIVE_BUTTON_TEXT,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    @Override
    public void responseFromServer(Object listener) {
        if (listener instanceof GetEntityId)
        {
            if (getEntityId.getLocationSuggestions().length > 0)
            {
                mApiCall.listOfrestaurants(getEntityId.getLocationSuggestions()[0].getEntityId(), getEntityId.getLocationSuggestions()[0].getEntityType(), this);
                if (nameOfClickedNavigationItem == null)
                {
                    mApiCall.listOfZomatoCollection(getEntityId.getLocationSuggestions()[0].getCityId(), this);
                }

            }
            else
            {
                ToastUtility.showToast(getApplicationContext(), ZOMATO_SERVICE_STATUS);
                mFetchProgress.setVisibility(View.GONE);
                mCurrentLocationTv.setText("");
            }
        }
        else if (listener instanceof Example)
        {
            if (restaurants.getRestaurants().size() > 0)
            {
                if (this.nameOfClickedNavigationItem != null)
                {
                    ArrayList<Restaurant> al_restaurants = new ArrayList<>(restaurants.getRestaurants().size());
                    al_restaurants.addAll(restaurants.getRestaurants());

                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    NavigationFragment.mRestaurantRecyclerView.setLayoutManager(mLayoutManager);
                    NavigationFragment.mRestaurantRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    NavigationFragment.mRestaurantAdapter = new RestaurantAdapter(al_restaurants, getApplicationContext(), R.layout.nav_fragment_data);
                    NavigationFragment.mRestaurantRecyclerView.setAdapter(NavigationFragment.mRestaurantAdapter);
                    nameOfClickedNavigationItem = null;

                }
                else
                {
                    ArrayList<Restaurant> al_restaurants = new ArrayList<>(restaurants.getRestaurants().size());
                    al_restaurants.addAll(restaurants.getRestaurants());

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mAdapter = new RestaurantAdapter(al_restaurants, getApplicationContext(), R.layout.resturants_details);
                    mRecyclerView.setAdapter(mAdapter);


                }
            }
            else
            {
                ToastUtility.showToast(getApplicationContext(), ZOMATO_SERVICE_STATUS);
            }
            MainActivity.mRecommendationTv.setVisibility(View.VISIBLE);
            mFetchProgress.setVisibility(View.GONE);
        }
        else if (listener instanceof ZomatoCollection)
        {
            if (zomatoCollection.getCollections().size() > 0)
            {
                ArrayList<Collections> al_zomatoCollections = new ArrayList<>(zomatoCollection.getCollections().size());
                al_zomatoCollections.addAll(zomatoCollection.getCollections());

                mTitle.setText(StringFormatter.normalCaseStringConverter(zomatoCollection.getDisplayText().toLowerCase()));
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewForZomatoCollection.setLayoutManager(mLayoutManager);
                mRecyclerViewForZomatoCollection.setItemAnimator(new DefaultItemAnimator());
                mAdapterForZomatoCollection = new ZomatoCollectionAdapter(al_zomatoCollections, getApplicationContext(), R.layout.zomato_collection_details);
                mRecyclerViewForZomatoCollection.setAdapter(mAdapterForZomatoCollection);

            }
            else
            {
                ToastUtility.showToast(getApplicationContext(), ZOMATO_SERVICE_STATUS);
            }
        }
        else if (listener instanceof GetCategoryListForNavigationMenu)
        {
            mNavProgressBar.setVisibility(View.GONE);
            mNavAdapter = new CategoryListForNavigationMenuAdapter(categoryList.getCategories(), MainActivity.this);
            mNavRecyclerView.setAdapter(mNavAdapter);
        }
        else
        {
            ToastUtility.showToast(getApplicationContext(), listener.toString());
        }
    }
}
