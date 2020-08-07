package com.phenix.app.book.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phenix.app.book.R;
import com.phenix.app.book.data.AppDatabase;
import com.phenix.app.book.ui.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

/**
 * MainActivity
 *
 * @author john
 * @since 2020-05-23
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db_book_info")
                             .createFromAsset("databases/db_books.db")
                             .allowMainThreadQueries()
                             .build();

        if (db.bookInfoDao().getAll().size() > 0) {
            TextView msgA = findViewById(R.id.tv_msg);
            msgA.setText(new Gson().toJson(db.bookInfoDao().getAll().get(0)));
        } else {
            Log.d("book_info", "found no books");
        }
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
