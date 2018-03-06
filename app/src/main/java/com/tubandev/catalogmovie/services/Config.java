package com.tubandev.catalogmovie.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tubandev.catalogmovie.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public class Config {

    private static Retrofit retrofit = null;
    private static OkHttpClient client;
    protected static String URL = "https://api.themoviedb.org/3/";

    protected static Retrofit getClient(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (BuildConfig.DEBUG) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }

        if (retrofit==null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client);
            retrofit = builder.build();
        }

        return retrofit;
    }

    public static ApiEndpoint getService(){
        ApiEndpoint apiEndpoint = getClient(URL).create(ApiEndpoint.class);
        return apiEndpoint;
    }
}
