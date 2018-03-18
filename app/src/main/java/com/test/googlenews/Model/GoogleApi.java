package com.test.googlenews.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pyrov on 15.03.2018.
 */

public interface GoogleApi {
    @GET("/v2/top-headlines")
    Call<News> getData(@Query("country") String resourceName, @Query("apiKey") String key);

    @GET("/v2/everything")
    Call<News> getRequest(@Query("q") String resourceName, @Query("sortBy") String sortBy, @Query("pageSize") int pageSize,@Query("apiKey") String key);
}
