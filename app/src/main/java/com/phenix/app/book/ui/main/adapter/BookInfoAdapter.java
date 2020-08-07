package com.phenix.app.book.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.phenix.app.book.data.local.entity.BookInfo;
import com.phenix.app.book.databinding.ItemBookInfoBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BookInfoAdapter
 *
 * @author john
 * @since 2020-08-07
 */
public class BookInfoAdapter extends RecyclerView.Adapter<BookInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<BookInfo> mData;

    public BookInfoAdapter(Context context, List<BookInfo> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookInfoBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = mData.get(position);
        holder.mBinding.tvTitle.setVisibility(View.VISIBLE);
        holder.mBinding.tvAuthor.setVisibility(View.VISIBLE);
        holder.mBinding.ivCover.setVisibility(View.GONE);

        holder.mBinding.tvTitle.setText(bookInfo.name);
        holder.mBinding.tvAuthor.setText(bookInfo.author);

        Glide.with(mContext)
             .load(bookInfo.cover)
             .into(new CustomTarget<Drawable>() {
                 @Override
                 public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                     holder.mBinding.tvTitle.setVisibility(View.GONE);
                     holder.mBinding.tvAuthor.setVisibility(View.GONE);
                     holder.mBinding.ivCover.setVisibility(View.VISIBLE);

                     holder.mBinding.ivCover.setImageDrawable(resource);
                 }

                 @Override
                 public void onLoadCleared(@Nullable Drawable placeholder) {
                     holder.mBinding.tvTitle.setVisibility(View.VISIBLE);
                     holder.mBinding.tvAuthor.setVisibility(View.VISIBLE);
                     holder.mBinding.ivCover.setVisibility(View.GONE);

                     holder.mBinding.tvTitle.setText(bookInfo.name);
                     holder.mBinding.tvAuthor.setText(bookInfo.author);
                 }
             });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBookInfoBinding mBinding;

        public ViewHolder(ItemBookInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
