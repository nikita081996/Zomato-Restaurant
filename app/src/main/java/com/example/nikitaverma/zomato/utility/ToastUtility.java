package com.example.nikitaverma.zomato.utility;

import android.content.Context;
import android.widget.Toast;

public class ToastUtility {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
