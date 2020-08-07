package com.phenix.app.book.data.local;

import android.content.Context;

import com.phenix.app.book.data.local.dao.BookInfoDao;
import com.phenix.app.book.data.local.entity.BookInfo;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * AppDatabase
 *
 * @author john
 * @since 2020-08-06
 */
@Database(entities = {BookInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookInfoDao bookInfoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "db_book_info")
                                   .createFromAsset("databases/db_books.db")
                                   .fallbackToDestructiveMigration()
                                   .build();
                }
            }
        }

        return INSTANCE;
    }
}
