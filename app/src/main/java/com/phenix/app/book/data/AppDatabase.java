package com.phenix.app.book.data;

import com.phenix.app.book.data.dao.BookInfoDao;
import com.phenix.app.book.data.model.BookInfo;

import androidx.room.Database;
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
}
