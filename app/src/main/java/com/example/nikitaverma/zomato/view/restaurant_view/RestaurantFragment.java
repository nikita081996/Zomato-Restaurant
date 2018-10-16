package com.example.nikitaverma.zomato.view.restaurant_view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.adapter.RestaurantAdapter;
import com.example.nikitaverma.zomato.adapter.ZomatoCollectionAdapter;

/**
 * MainActivity Fragment for List of Restaurant
 */
public class RestaurantFragment extends Fragment {
    public static RestaurantAdapter mAdapter;
    public static ZomatoCollectionAdapter mAdapterForZomatoCollection;
    public static RecyclerView mRecyclerView;
    public static RecyclerView mRecyclerViewForZomatoCollection;
    public static TextView mTitle;

    public RestaurantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resturant, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_resturants);
        mRecyclerViewForZomatoCollection = view.findViewById(R.id.recycler_view_zomato_collection);
        mTitle = view.findViewById(R.id.display_text);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
