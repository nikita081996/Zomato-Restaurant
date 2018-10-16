package com.example.nikitaverma.zomato.view.restaurant_view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.adapter.DailyMenuAdapter;
import com.example.nikitaverma.zomato.api_call.ApiCall;
import com.example.nikitaverma.zomato.api_call.ResponseListner;
import com.example.nikitaverma.zomato.model.daily_menu.DailyMenu;
import com.example.nikitaverma.zomato.model.search_for_resturants.Restaurant;
import com.example.nikitaverma.zomato.utility.Constants;
import com.example.nikitaverma.zomato.utility.MyFragment;
import com.example.nikitaverma.zomato.utility.ToastUtility;
import com.example.nikitaverma.zomato.view.daily_menu.DailyMenuFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * class for Restaurant details
 */
public class RestaurantDetailsActivity extends AppCompatActivity implements View.OnClickListener, Constants, ResponseListner {

    public static TextView mMenuAvailabilityTv;
    private Menu menu;
    private List<Restaurant> mRestaurantList;
    private int mPosition;
    private TextView mRestaurantNameTv;
    private TextView mRestaurantAddressTv;
    private TextView mRestaurantCuisinesTv;
    private TextView mRestaurantRatingTv;
    private TextView mRestaurantOrderNowTv;
    private AppBarLayout mAppBarLayout;
    private ImageView mRestaurantImageView;
    private ProgressBar mRestaurantProgressBar;
    private TextView mRestaurantVotesTv;
    private ApiCall mApiCall;
    private TextView mDailyMenuTv;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_details2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initialize();
        setmAppBarLayout();

        Intent intent = getIntent();
        mPosition = intent.getIntExtra(ADAPTER_POSITION, 0);
        mRestaurantList = (List<Restaurant>) intent.getSerializableExtra(LIST_OF_RESTAURANT_DETAILS);

        setData();
        //Fetch menu list based on restaurant id
        callMenuaApi();
    }

    private void initialize() {
        mApiCall = new ApiCall();
        mRestaurantNameTv = findViewById(R.id.restaurant_name);
        mRestaurantAddressTv = findViewById(R.id.restaurant_address);
        mRestaurantCuisinesTv = findViewById(R.id.restaurant_cuisines);
        mRestaurantRatingTv = findViewById(R.id.restaurant_rating);
        mRestaurantOrderNowTv = findViewById(R.id.restaurant_ordernow);
        mRestaurantImageView = findViewById(R.id.restaurant_image);
        mRestaurantProgressBar = findViewById(R.id.restaurant_progress_bar);
        mRestaurantVotesTv = findViewById(R.id.votes);
        mMenuAvailabilityTv = findViewById(R.id.menu_availability);
        mDailyMenuTv = findViewById(R.id.daily_menu_tv);

        mRestaurantOrderNowTv.setOnClickListener(this);
    }

    private void setmAppBarLayout() {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        mAppBarLayout = findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    collapsingToolbarLayout.setTitle(" ");
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    collapsingToolbarLayout.setTitle("Restaurant Details");
                } else if (isShow) {
                    isShow = false;
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }


    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.restaurant_ordernow:
                ToastUtility.showToast(getApplicationContext(), ORDER_NOW);
                break;
        }
    }

    private void setData() {
        ToastUtility.showToast(getApplicationContext(), mRestaurantList.get(mPosition).getRestaurant().getName());
        mRestaurantNameTv.setText(mRestaurantList.get(mPosition).getRestaurant().getName());
        mRestaurantAddressTv.setText(mRestaurantList.get(mPosition).getRestaurant().getLocation().getAddress());
        mRestaurantRatingTv.setText(mRestaurantList.get(mPosition).getRestaurant().getUserRating().getAggregateRating());
        mRestaurantVotesTv.setText(mRestaurantList.get(mPosition).getRestaurant().getUserRating().getVotes() + " Votes");
        mRestaurantCuisinesTv.setText(mRestaurantList.get(mPosition).getRestaurant().getCuisines());

        if (mRestaurantList.get(mPosition).getRestaurant().getDeliveringNow() == 1)
            mRestaurantOrderNowTv.setVisibility(View.VISIBLE);

        if (!(mRestaurantList.get(mPosition).getRestaurant().getFeaturedImage()).isEmpty()) {
            Picasso.with(getApplicationContext()).load(mRestaurantList.get(mPosition).getRestaurant().getFeaturedImage())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .noFade().resize(200, 200)
                    .into(mRestaurantImageView, new Callback() {
                        @Override
                        public void onError() {
                            mRestaurantProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onSuccess() {
                            mRestaurantProgressBar.setVisibility(View.GONE);
                        }

                    });
        } else {
            mRestaurantImageView.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.ic_launcher_foreground));
            mRestaurantProgressBar.setVisibility(View.GONE);
        }
    }

    private void callMenuaApi() {
        mApiCall.getDailyMenuList(mRestaurantList.get(mPosition).getRestaurant().getR().getResId(), this);

        DailyMenuFragment fragment = new DailyMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        MyFragment myFragment = new MyFragment();
        myFragment.getFragment(fm, fragment, R.id.daily_menu_frame_layout);
    }

    @Override
    public void responseFromServer(Object listener) {
        if (listener instanceof DailyMenu) {
            if (DailyMenuFragment.dailyMenu.getDailyMenus().length > 0 && DailyMenuFragment.dailyMenu.getDailyMenus()[0].getDailyMenu().getDishes().length > 0) {
                mDailyMenuTv.setVisibility(View.VISIBLE);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                DailyMenuFragment.mRecyclerViewDailyMenu.setLayoutManager(mLayoutManager);
                DailyMenuFragment.mRecyclerViewDailyMenu.setItemAnimator(new DefaultItemAnimator());
                DailyMenuFragment.mDailyMenuAdapter = new DailyMenuAdapter(Arrays.asList(DailyMenuFragment.dailyMenu.getDailyMenus()), getApplicationContext(), R.layout.daily_menu_data);
                DailyMenuFragment.mRecyclerViewDailyMenu.setAdapter(DailyMenuFragment.mDailyMenuAdapter);


            } else {
                ToastUtility.showToast(getApplicationContext(), NO_MENU_AVAILABLE_FOR_THIS_RESTAURANT);
                mMenuAvailabilityTv.setVisibility(View.VISIBLE);

            }
        } else {
            ToastUtility.showToast(getApplicationContext(), listener.toString());
        }
    }
}
