package com.example.nikitaverma.zomato.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MyFragment extends AppCompatActivity {

    public static void getFragment(FragmentManager fm, Fragment fragment, int resource) {

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(resource, fragment);
        ft.replace(resource, fragment);
        ft.commit();

    }
}
