package com.phenix.app.book.utils;

import java.util.Calendar;

/**
 * TimeUtils
 *
 * @author john
 * @since 2020-05-24
 */
public final class TimeUtils {
    private TimeUtils() {}

    public static boolean isNight() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        return (currentHour <= 7 || currentHour >= 18);
    }
}
