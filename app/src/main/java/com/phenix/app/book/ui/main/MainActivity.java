package com.phenix.app.book.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.phenix.app.book.R;
import com.phenix.app.book.data.local.entity.BookInfo;
import com.phenix.app.book.databinding.ActivityMainBinding;
import com.phenix.app.book.decoration.GridSpaceItemDecoration;
import com.phenix.app.book.ui.base.BaseActivity;
import com.phenix.app.book.ui.book.BookDetailActivity;
import com.phenix.app.book.ui.main.adapter.BookInfoAdapter;
import com.phenix.app.book.viewmodel.BookInfoViewModel;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

/**
 * MainActivity
 *
 * @author john
 * @since 2020-05-23
 */
public class MainActivity extends BaseActivity {
    private BookInfoAdapter mAdapter;
    private List<BookInfo> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mData = new ArrayList<>();
        mAdapter = new BookInfoAdapter(this, mData);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, VERTICAL, false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addItemDecoration(new GridSpaceItemDecoration(2, 24, true));

        BookInfoViewModel bookInfoViewModel = new ViewModelProvider(this).get(BookInfoViewModel.class);
        bookInfoViewModel.getAllBookInfo().observe(this, bookInfoList -> {
            if (CollectionUtils.isEmpty(bookInfoList)) {
                showSnackbar("book info list is empty!");
                return;
            }

            mData.clear();
            mData.addAll(bookInfoList);
            mAdapter.notifyDataSetChanged();
        });

        mAdapter.setItemClickListener(bookInfo -> {
            BookDetailActivity.start(MainActivity.this, bookInfo.isbn);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_theme) {
            changeUIMode();
        }

        return true;
    }

    private void changeUIMode() {
        int mode = AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;
        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO) {
            mode = AppCompatDelegate.MODE_NIGHT_YES;
        }

        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
