package com.test.googlenews;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.test.googlenews.Model.App;
import com.test.googlenews.Model.ArticlesItem;
import com.test.googlenews.Model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ArticlesItem> itemList;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        itemList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        newsAdapter = new NewsAdapter(this, itemList);
        recyclerView.setAdapter(newsAdapter);

        App.getGoogleApi().getData("google-news-ru", "1d48cf2bd8034be59054969db665e62e").enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
               if (response.body() != null) {
                   newsAdapter.setData(response.body().getArticles());
               }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                Log.e("MyTAG", String.valueOf(t));
            }
        });
    }
}
