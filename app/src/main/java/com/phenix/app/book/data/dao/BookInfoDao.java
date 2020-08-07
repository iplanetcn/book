package com.phenix.app.book.data.dao;

import com.phenix.app.book.data.model.BookInfo;

import java.util.List;

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
    List<BookInfo> getAll();

    @Query("SELECT * FROM books_info LIMIT :size OFFSET :page")
    List<BookInfo> getBooks(int page, int size);
}
