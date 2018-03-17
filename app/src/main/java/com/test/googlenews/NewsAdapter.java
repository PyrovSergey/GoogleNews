package com.test.googlenews;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.googlenews.Model.ArticlesItem;

import java.util.List;

/**
 * Created by pyrov on 16.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<ArticlesItem> itemList;

    public NewsAdapter(Context context, List<ArticlesItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesItem currentItem = itemList.get(position);
        if (currentItem.getUrlToImage() != null & !currentItem.getUrlToImage().equals("")) {
            Picasso.with(context).load(currentItem.getUrlToImage()).placeholder(R.drawable.gn_holder).into(holder.imageView);
        } else {
            Picasso.with(context).load(R.drawable.gn_holder).into(holder.imageView);
        }
        holder.textViewTitle.setText(String.valueOf(currentItem.getTitle()));
        holder.textViewDescription.setText(String.valueOf(currentItem.getDescription()));
        holder.textViewPublishedAt.setText(String.valueOf(currentItem.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPublishedAt;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) view.findViewById(R.id.image_news);
            textViewTitle = (TextView) view.findViewById(R.id.title);
            textViewDescription = (TextView) view.findViewById(R.id.description);
            textViewPublishedAt = (TextView) view.findViewById(R.id.publishedAt);
        }
    }
}
