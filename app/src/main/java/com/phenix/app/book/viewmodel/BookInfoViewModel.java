package com.phenix.app.book.viewmodel;

import android.app.Application;

import com.phenix.app.book.data.Repository;
import com.phenix.app.book.data.local.entity.BookInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * BookInfoViewModel
 *
 * @author john
 * @since 2020-08-07
 */
public class BookInfoViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<List<BookInfo>> mAllBookInfo;

    public BookInfoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllBookInfo = mRepository.getAllBookInfo();
    }

    public LiveData<List<BookInfo>> getAllBookInfo() {
        return mAllBookInfo;
    }

    public LiveData<List<BookInfo>> getBookInfoList() {
        return mRepository.getBookInfoList(0, 20);
    }
}
