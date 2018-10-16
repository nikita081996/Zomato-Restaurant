package com.example.nikitaverma.zomato.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.model.search_for_resturants.Restaurant;
import com.example.nikitaverma.zomato.utility.Constants;
import com.example.nikitaverma.zomato.view.restaurant_view.RestaurantDetailsActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Adapter class for Restaurant Details of mainActivity
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements Constants {

    private final List<Restaurant> mRestaurantList;
    private final Context mContext;
    private int mResource;

    public RestaurantAdapter(List<Restaurant> resturantList, Context context, int resource) {
        this.mRestaurantList = resturantList;
        this.mContext = context;
        this.mResource = resource;
    }

    /**
     * Inflate xml file to java
     *
     * @param parent
     * @param viewType
     * @return type of view holder
     */
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mResource, parent, false);

        return new RestaurantViewHolder(itemView);
    }

    /**
     * Bind each data to view
     *
     * @param holder
     * @param position of holder
     */
    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, int position) {
        holder.restaurantName.setText(mRestaurantList.get(position).getRestaurant().getName());
        holder.restaurantAddress.setText("" + mRestaurantList.get(position).getRestaurant().getLocation().getAddress());
        holder.cuisines.setText("" + mRestaurantList.get(position).getRestaurant().getCuisines());

        if (!(mRestaurantList.get(position).getRestaurant().getFeaturedImage()).isEmpty()) {
            Picasso.with(mContext).load(mRestaurantList.get(position).getRestaurant().getFeaturedImage())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .noFade().resize(200, 200)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onError() {
                            //  holder.imageView.setImageDrawable(ic_launcher_foreground);
                            holder.progressBar.setVisibility(View.GONE);
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                            holder.progressBar.setVisibility(View.GONE);
                        }

                    });
        } else {
            holder.imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher_foreground));
            holder.progressBar.setVisibility(View.GONE);
        }

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mContext, RestaurantDetailsActivity.class);
                intent.putExtra(ADAPTER_POSITION, position);
                intent.putExtra(LIST_OF_RESTAURANT_DETAILS, (Serializable) mRestaurantList);
                mContext.startActivity(intent);
            }
        });
    }
    //  }

    /**
     * @return size of listView
     */
    @Override
    public int getItemCount() {
        if (mRestaurantList != null)
            return mRestaurantList.size();
        else
            return 0;
    }

    /**
     * holder to each object in view
     */
    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView cuisines;
        private ImageView imageView;
        private ProgressBar progressBar;
        private ItemClickListener clickListener;

        RestaurantViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.name_of_restaurants);
            restaurantAddress = view.findViewById(R.id.address_of_restaurants);
            cuisines = view.findViewById(R.id.cusinies);
            imageView = view.findViewById(R.id.image_view_col);
            progressBar = view.findViewById(R.id.progressbar);
            view.setOnClickListener(this);
        }

        private void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition());
        }
    }

}