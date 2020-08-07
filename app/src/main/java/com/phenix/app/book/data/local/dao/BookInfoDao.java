package com.phenix.app.book.data.local.dao;

import com.phenix.app.book.data.local.entity.BookInfo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

/**
 * BookInfoDao
 *
 * @author john
 * @since 2020-08-06
 */
@Dao
public interface BookInfoDao {
    @Query("SELECT * FROM books_info")
    LiveData<List<BookInfo>> getAll();

    @Query("SELECT * FROM books_info LIMIT :size OFFSET :page")
    LiveData<List<BookInfo>> getBooks(int page, int size);
}
