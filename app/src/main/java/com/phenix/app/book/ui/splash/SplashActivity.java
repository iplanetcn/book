package com.phenix.app.book.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.phenix.app.book.ui.base.BaseActivity;
import com.phenix.app.book.ui.main.MainActivity;

/**
 * SplashActivity
 *
 * @author john
 * @since 2020-05-23
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
