package com.phenix.app.book;

import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String DB_NAME = "db_books.db";
    private static final String PATH = "db_books.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDatabase();
    }

    private void loadDatabase() {
        File checkDB;
        try {
            checkDB = new File(getFilesDir() + "/" + PATH + "/" + DB_NAME);
            if (!checkDB.exists()) {
                InputStream ins = getApplicationContext().getAssets().open(PATH + "/" + DB_NAME);
                OutputStream ous = new FileOutputStream(getFilesDir() + "/" + DB_NAME);

                byte[] buff = new byte[1024];
                int length;
                while ((length = ins.read(buff)) > 0) {
                    ous.write(buff, 0 , length);
                }

                ous.flush();
                ous.close();
                ins.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
