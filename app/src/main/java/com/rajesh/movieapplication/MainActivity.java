package com.rajesh.movieapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.rajesh.movieapplication.model.Movie;
import com.rajesh.movieapplication.model.MovieDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    //http://api.themoviedb.org/3/movie/popular?page=1&&api_key=3d9f6ef05faa3072ee2caf7fb6870964

    GridView gridView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        movieAdapter = new MovieAdapter(MainActivity.this, new ArrayList<Movie>());
        gridView.setAdapter(movieAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService service = retrofit.create(MovieService.class);
        Call<MovieDTO> moviesDTOCall = service.getMovies("popular", 1, "3d9f6ef05faa3072ee2caf7fb6870964");

        moviesDTOCall.enqueue(new Callback<MovieDTO>() {
            @Override
            public void onResponse(Call<MovieDTO> call, Response<MovieDTO> response) {
                movieAdapter.addData(response.body().getResults());
                Log.d(TAG, "onResponse: " + response.body().getResults().get(0).getTitle());
                Toast.makeText(MainActivity.this, "size of " + response.body().getResults().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
