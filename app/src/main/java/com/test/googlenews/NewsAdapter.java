package com.test.googlenews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.googlenews.Model.ArticlesItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pyrov on 16.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<ArticlesItem> itemList = new ArrayList<>();

    public NewsAdapter(Context context, List<ArticlesItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<ArticlesItem> list) {
        itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticlesItem currentItem = itemList.get(position);
        if (currentItem.getUrlToImage() != null & !currentItem.getUrlToImage().equals("")) {
            Picasso.with(context).load(currentItem.getUrlToImage()).placeholder(R.drawable.gn_holder).into(holder.imageView);
        } else {
            Picasso.with(context).load(R.drawable.gn_holder).into(holder.imageView);
        }
        holder.textViewTitle.setText(String.valueOf(currentItem.getTitle()));
        holder.textViewDescription.setText(String.valueOf(currentItem.getDescription()));
        String data = getCurrentDate(currentItem.getPublishedAt());
        ////
        holder.textViewPublishedAt.setText(data);
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

    private String getCurrentDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date);
    }
}
