package com.tubandev.catalogmovie.services;

import com.tubandev.catalogmovie.model.MainResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public interface SearchEndpoint {

    @GET("search/movie?api_key=866634740b4188842c78eecae4c3c101&language=en-US")
    Call<MainResult> searchMovie(@Query("query") String name);

}
