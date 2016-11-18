package com.rajesh.movieapplication;

import com.rajesh.movieapplication.model.MovieDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieService {
    @GET("3/movie/{categories}")
    Call<MovieDTO> getMovies(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);
}
