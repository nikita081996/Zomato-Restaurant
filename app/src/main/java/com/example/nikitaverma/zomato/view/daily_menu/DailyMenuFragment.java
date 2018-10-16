package com.example.nikitaverma.zomato.view.daily_menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikitaverma.zomato.R;
import com.example.nikitaverma.zomato.adapter.DailyMenuAdapter;
import com.example.nikitaverma.zomato.model.daily_menu.DailyMenu;

/**
 * Fragment for Daily Menu list
 */
public class DailyMenuFragment extends Fragment {

    public static RecyclerView mRecyclerViewDailyMenu;
    public static DailyMenuAdapter mDailyMenuAdapter;
    public static DailyMenu dailyMenu;
    public DailyMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_menu, container, false);
        mRecyclerViewDailyMenu = view.findViewById(R.id.recycler_view_daily_menu);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
