package com.example.nikitaverma.zomato.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.model.daily_menu.DailyMenus;
import com.example.nikitaverma.zomato.model.daily_menu.Dishes;
import com.example.nikitaverma.zomato.utility.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter class for Daily Menu List
 */
public class DailyMenuAdapter extends RecyclerView.Adapter<DailyMenuAdapter.DailyMenuViewHolder> implements Constants {

    private final List<DailyMenus> mDailyMenusList;
    private final Context mContext;
    private int mResource;
    private List<Dishes> dishes;

    public DailyMenuAdapter(List<DailyMenus> dailyMenusList, Context context, int resource) {
        this.mDailyMenusList = dailyMenusList;
        this.mContext = context;
        this.mResource = resource;
        dishes = new ArrayList<Dishes>();
        for (DailyMenus dm : mDailyMenusList) {
            dishes.addAll(Arrays.asList(dm.getDailyMenu().getDishes()));
        }
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
    public DailyMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mResource, parent, false);

        return new DailyMenuViewHolder(itemView);
    }

    /**
     * Bind each data to view
     *
     * @param holder
     * @param position of holder
     */
    @Override
    public void onBindViewHolder(@NonNull final DailyMenuViewHolder holder, int position) {
        holder.dashName.setText(dishes.get(position).getDish().getName());
        holder.price.setText("" + dishes.get(position).getDish().getPrice());
    }
    //  }

    /**
     * @return size of listView
     */
    @Override
    public int getItemCount() {
        if (dishes != null)
            return dishes.size();
        else
            return 0;
    }

    /**
     * holder to each object in view
     */
    public class DailyMenuViewHolder extends RecyclerView.ViewHolder {
        private TextView dashName;
        private TextView price;

        DailyMenuViewHolder(View view) {
            super(view);
            dashName = view.findViewById(R.id.dish_name);
            price = view.findViewById(R.id.price);

        }

    }

}