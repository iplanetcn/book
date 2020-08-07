package com.phenix.app.book.ui.book;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.phenix.app.book.R;
import com.phenix.app.book.databinding.ActivityBookDetailBinding;
import com.phenix.app.book.ui.base.BaseActivity;
import com.phenix.app.book.viewmodel.BookInfoViewModel;

public class BookDetailActivity extends BaseActivity {

    public static void start(Context context, String isbn) {
        Intent starter = new Intent(context, BookDetailActivity.class);
        starter.putExtra("ISBN", isbn);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookDetailBinding binding = ActivityBookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        String isbn = getIntent().getStringExtra("ISBN");
        BookInfoViewModel bookInfoViewModel = new ViewModelProvider(this).get(BookInfoViewModel.class);
        bookInfoViewModel.getBookInfoByIsbn(isbn).observe(this, bookInfo -> {
            if (bookInfo == null) {
                showSnackbar("book info is null!");
                return;
            }

            binding.toolbarLayout.setTitle(bookInfo.name);
            binding.fab.setOnClickListener(view ->
                    Snackbar.make(view, String.format("ISBN:%s", bookInfo.isbn), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show());
            ((MaterialTextView) binding.getRoot().findViewById(R.id.tv_book_description)).setText(bookInfo.description);
            Glide.with(this)
                    .load(bookInfo.cover)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource,
                                                    @Nullable Transition<? super Drawable> transition) {

                            binding.toolbarLayout.setBackground(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });

        });
    }
}