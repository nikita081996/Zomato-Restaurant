package com.example.nikitaverma.zomato.view.category_view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.adapter.RestaurantAdapter;

/**
 * Fragment for Navigation View
 */
public class NavigationFragment extends Fragment {

    public static RestaurantAdapter mRestaurantAdapter;
    public static RecyclerView mRestaurantRecyclerView;
    private Context mContext;

    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mContext = container.getContext();

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        NavigationFragment.mRestaurantRecyclerView = view.findViewById(R.id.nav_fragment_recycler_view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
