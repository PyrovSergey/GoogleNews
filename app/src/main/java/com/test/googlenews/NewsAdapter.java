package com.test.googlenews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.test.googlenews.Model.ArticlesItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pyrov on 16.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static final String DATE_PATTERN = "hh:mm  dd MMMM yyyy";

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
        if (itemList.isEmpty()) {
            Toast.makeText(context, "No news", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ArticlesItem currentItem = itemList.get(position);
        try {
            if (!TextUtils.isEmpty(currentItem.getUrlToImage())) {
                Picasso.with(context).load(currentItem.getUrlToImage()).placeholder(R.drawable.gn_holder).into(holder.imageView);
            } else {
                Picasso.with(context).load(R.drawable.gn_holder).into(holder.imageView);
            }
        } catch (NullPointerException e) {
            Picasso.with(context).load(R.drawable.gn_holder).into(holder.imageView);
        }
        holder.textViewTitle.setText(String.valueOf(currentItem.getTitle()));
        String description = String.valueOf(currentItem.getDescription());
        if (description.equals("null")) {
            description = "";
        }
        holder.textViewDescription.setText(description);
        holder.textViewPublishedAt.setText(getStringDate(currentItem.getPublishedAt()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getUrl())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        CardView cardView;
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPublishedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            cardView = (CardView) view.findViewById(R.id.card_view);
            imageView = (ImageView) view.findViewById(R.id.image_news);
            textViewTitle = (TextView) view.findViewById(R.id.title);
            textViewDescription = (TextView) view.findViewById(R.id.description);
            textViewPublishedAt = (TextView) view.findViewById(R.id.publishedAt);
        }
    }

//    private String getDate(String string) {
//        if (TextUtils.isEmpty(string)) {
//            return "";
//        }
//        String year = string.substring(0, 4);
//        String month = string.substring(5, 7);
//        String date = string.substring(8, 10);
//        String hour = string.substring(11, 13);
//        String minute = string.substring(14, 16);
//        //Log.e("MyDATA", year + "|" + month + "|" + date + "|" + hour + ":" + minute);
//        return hour + ":" + minute + "  " + date + "." + month + "." + year;
//    }

    private String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        return dateFormat.format(date);
    }
}
