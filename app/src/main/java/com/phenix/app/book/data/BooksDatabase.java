package com.phenix.app.book.data;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BooksDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "db_books.db";
    private static final int DATABASE_VERSION = 1;

    public BooksDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
