package com.phenix.app.book.data;

import android.app.Application;

import com.phenix.app.book.data.local.AppDatabase;
import com.phenix.app.book.data.local.dao.BookInfoDao;
import com.phenix.app.book.data.local.entity.BookInfo;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Repository
 *
 * @author john
 * @since 2020-08-07
 */
public class Repository {
    private BookInfoDao mBookInfoDao;
    private LiveData<List<BookInfo>> mAllBookInfo;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mBookInfoDao = db.bookInfoDao();
        mAllBookInfo = mBookInfoDao.getAll();
    }

    public LiveData<List<BookInfo>> getAllBookInfo() {
        return mAllBookInfo;
    }

    public LiveData<List<BookInfo>> getBookInfoList(int page, int size) {
        return mBookInfoDao.getBooks(page, size);
    }
}
