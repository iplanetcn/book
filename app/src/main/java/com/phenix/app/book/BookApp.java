package com.phenix.app.book;

import android.app.Application;

import com.phenix.app.book.utils.TimeUtils;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * BookApp
 *
 * @author john
 * @since 2020-05-24
 */
public class BookApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Get UI mode and set
        int mode = AppCompatDelegate.MODE_NIGHT_NO;

        if (TimeUtils.isNight()) {
            mode = AppCompatDelegate.MODE_NIGHT_YES;
        }

        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
