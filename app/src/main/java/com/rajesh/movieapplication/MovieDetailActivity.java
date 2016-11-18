package com.rajesh.movieapplication;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rajesh.movieapplication.model.Movie;


public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIES_OBJECT = "movie";
    public static final String MOVIE_OBJECT_BUNDLE = "movie_bundle";
    private static final String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private Movie movie;

    ImageView moviePoster;
    TextView movieTitle, releasingDate, overView;
    RatingBar ratingBar;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initializeView();

        //get data passed from bundle
        movie = getIntent().getExtras().getBundle(MOVIE_OBJECT_BUNDLE).getParcelable(MOVIES_OBJECT);

        setToolbar();
        setData();
    }

    private void initializeView() {
        moviePoster = (ImageView) findViewById(R.id.img_movie_poster);
        movieTitle = (TextView) findViewById(R.id.tv_movie_title);
        releasingDate = (TextView) findViewById(R.id.tv_releasing_date);
        overView = (TextView) findViewById(R.id.tv_overview);
        ratingBar = (RatingBar) findViewById(R.id.rb_movie_rating);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setData() {
        Glide.with(this).load(getImageUri(movie.getPosterPath())).into(moviePoster);
        movieTitle.setText(movie.getTitle());
        ratingBar.setRating(movie.getVoteCount());
        overView.setText(movie.getOverview());
        releasingDate.setText(movie.getReleaseDate());
    }

    private void setToolbar() {
        //set the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle(movie.getTitle());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    public String getImageUri(String uri) {
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }
}
