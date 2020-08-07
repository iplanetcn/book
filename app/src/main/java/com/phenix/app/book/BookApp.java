package com.phenix.app.book;

import android.app.Application;
import android.util.Log;

import com.phenix.app.book.utils.TimeUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import timber.log.Timber;

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
        initDayNightMode();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    private void initDayNightMode() {
        // Get UI mode and set
        int mode = AppCompatDelegate.MODE_NIGHT_NO;

        if (TimeUtils.isNight()) {
            mode = AppCompatDelegate.MODE_NIGHT_YES;
        }

        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
