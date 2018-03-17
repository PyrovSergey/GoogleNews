package com.test.googlenews;

import com.test.googlenews.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pyrov on 15.03.2018.
 */

public interface GoogleApi {
    @GET("/v2/top-headlines")
    Call<News> getData(@Query("sources") String resourceName, @Query("apiKey") String key);
}
