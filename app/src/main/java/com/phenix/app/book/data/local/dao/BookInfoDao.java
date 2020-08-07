package com.phenix.app.book.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.phenix.app.book.data.local.entity.BookInfo;

import java.util.List;

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

    @Query("select * from books_info where isbn=:isbn")
    LiveData<BookInfo> getBookInfoByIsbn(String isbn);
}
