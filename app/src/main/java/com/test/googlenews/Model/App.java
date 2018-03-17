package com.test.googlenews.Model;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pyrov on 15.03.2018.
 */

public class App extends Application {
    private static GoogleApi googleApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        googleApi = retrofit.create(GoogleApi.class);
    }

    public static GoogleApi getGoogleApi() {
        return googleApi;
    }

}
