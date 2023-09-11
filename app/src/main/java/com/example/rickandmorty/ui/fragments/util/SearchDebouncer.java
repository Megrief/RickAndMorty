package com.example.rickandmorty.ui.fragments.util;

import android.os.Handler;
import android.os.Looper;

public class SearchDebouncer {
    private final static long DELAY = 2000L;
    private final static Handler handler = new Handler(Looper.getMainLooper());

    public static void searchDebounce(Runnable request) {
        handler.removeCallbacks(request);
        handler.postDelayed(request, DELAY);
    }



}
