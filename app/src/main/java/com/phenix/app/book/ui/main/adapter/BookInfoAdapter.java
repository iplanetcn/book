package com.phenix.app.book.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.phenix.app.book.data.local.entity.BookInfo;
import com.phenix.app.book.databinding.ItemBookInfoBinding;

import java.util.List;

/**
 * BookInfoAdapter
 *
 * @author john
 * @since 2020-08-07
 */
public class BookInfoAdapter extends RecyclerView.Adapter<BookInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<BookInfo> mData;
    private OnBookInfoItemClickListener mItemClickListener;

    public BookInfoAdapter(Context context, List<BookInfo> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, ItemBookInfoBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mData.get(position), mItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItemClickListener(OnBookInfoItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBookInfoBinding mBinding;
        Context mContext;

        public ViewHolder(Context context, ItemBookInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mContext = context;
        }

        public void bind(BookInfo bookInfo, @Nullable OnBookInfoItemClickListener onItemClickListener) {
            mBinding.tvTitle.setVisibility(View.VISIBLE);
            mBinding.tvAuthor.setVisibility(View.VISIBLE);
            mBinding.ivCover.setVisibility(View.GONE);

            mBinding.tvTitle.setText(bookInfo.name);
            mBinding.tvAuthor.setText(bookInfo.author);

            Glide.with(mContext)
                    .load(bookInfo.cover)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource,
                                                    @Nullable Transition<? super Drawable> transition) {
                            mBinding.tvTitle.setVisibility(View.GONE);
                            mBinding.tvAuthor.setVisibility(View.GONE);
                            mBinding.ivCover.setVisibility(View.VISIBLE);

                            mBinding.ivCover.setBackgroundDrawable(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            mBinding.tvTitle.setVisibility(View.VISIBLE);
                            mBinding.tvAuthor.setVisibility(View.VISIBLE);
                            mBinding.ivCover.setVisibility(View.GONE);

                            mBinding.tvTitle.setText(bookInfo.name);
                            mBinding.tvAuthor.setText(bookInfo.author);
                        }
                    });

            mBinding.cardView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(bookInfo);
                }
            });
        }
    }
}
